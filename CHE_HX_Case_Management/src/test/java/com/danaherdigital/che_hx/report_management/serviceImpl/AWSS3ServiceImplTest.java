package com.danaherdigital.che_hx.report_management.serviceImpl;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.agent.PowerMockAgent;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.springframework.test.util.ReflectionTestUtils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.danaherdigital.che_hx.report_management.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.report_management.serviceimpl.AWSS3ServiceImpl;

@PrepareForTest({AWSS3ServiceImpl.class,AmazonS3.class,IOUtils.class})
public class AWSS3ServiceImplTest {

	@InjectMocks
	AWSS3ServiceImpl aWSS3ServiceImpl;
	@Mock
	AmazonS3 amazonS3;

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	@Rule
	public PowerMockRule rule = new PowerMockRule();
	static {
		PowerMockAgent.initializeIfNeeded();
	}

	@Before
    public void setup() throws Exception {
        final File mockFile = mock(File.class);
        Mockito.doReturn(true).when(mockFile).exists();
        // Whatever other mockery you need.

        PowerMockito.whenNew(File.class).withAnyArguments()
                .thenReturn(mockFile);
        final FileOutputStream fileInputStreamMock = PowerMockito.mock(FileOutputStream.class);
        PowerMockito.whenNew(FileOutputStream.class).withAnyArguments()
                            .thenReturn(fileInputStreamMock);




    }


	@Test
	public void downloadFileSuccessTest() throws ChemtreatException, IOException {
		final File file1 = folder.newFile("myfile1.txt");

		S3Object s3Object = new S3Object();
		s3Object.setBucketName("");
		s3Object.setKey("");
		s3Object.setObjectContent(new FileInputStream(file1));

		ReflectionTestUtils.setField(aWSS3ServiceImpl, "bucketName", "aws.s3.bucket");
		PowerMockito.mockStatic(IOUtils.class);
		when(IOUtils.copy(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(1L);

		when(amazonS3.getObject(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(s3Object);

		File file = aWSS3ServiceImpl.downloadFile("test.pdf");
		assertNotEquals(null, file);
	}

	@Test
	public void downloadFileExceptionTest() throws ChemtreatException, IOException {

		ReflectionTestUtils.setField(aWSS3ServiceImpl, "bucketName", "aws.s3.bucket");

		when(amazonS3.getObject(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(null);

		File file = aWSS3ServiceImpl.downloadFile("test.pdf");
		assertNotEquals(null, file);
	}

}
