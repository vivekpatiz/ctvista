package com.danaherdigital.che_hx.report_management.serviceimpl;

import java.io.File;
import java.io.FileOutputStream;


import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.danaherdigital.che_hx.report_management.service.AWSS3Service;
import com.danaherdigital.che_hx.report_management.utils.ApplicationConstants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AWSS3ServiceImpl implements AWSS3Service {

	@Autowired
	private AmazonS3 amazonS3;
	@Value("${aws.s3.bucket}")
	private String bucketName;
	@Value("${aws.s3.folder_name}")
	private String folderName;

	@Value("${file.troubleshooting.guide.path}")
	private String fileTroubleShootPath;

	@Override
	// @Async annotation ensures that the method is executed in a different
	// background thread
	// but not consume the main thread.
	@Async
	public File downloadFile(final String keyName) {

		boolean b = FileUtils.deleteQuietly(new File(fileTroubleShootPath));
		log.info("file deleted={}", b);
		String filename = null;
		if (StringUtils.equals(ApplicationConstants.TROUBLE_SHOOTING_GUIDE, keyName)) {
			filename = ApplicationConstants.TROUBLE_SHOOTING_GUIDE;
		}

		S3Object s3Object = amazonS3.getObject(bucketName, folderName + "/" + filename);
		File s3File = new File("/opt/CHE_HX_Case_Management/" + filename);

		try (FileOutputStream fos = new FileOutputStream(s3File)) { // throws Exception
			IOUtils.copy(s3Object.getObjectContent(), fos);
		} catch (Exception e) {
			log.error("Exception Occurred while fetching file {}", e);
		}
		return s3File;

	}

}
