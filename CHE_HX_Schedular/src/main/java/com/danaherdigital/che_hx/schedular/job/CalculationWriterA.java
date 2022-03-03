package com.danaherdigital.che_hx.schedular.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.danaherdigital.che_hx.schedular.dto.SliceResponseDTO;
import com.danaherdigital.che_hx.schedular.model.AssetDataLog;
import com.danaherdigital.che_hx.schedular.repo.AssetDataLogRepository;
import com.danaherdigital.che_hx.schedular.repo.CalcJobRepository;
import com.danaherdigital.che_hx.schedular.utils.ApplicationConstants;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class CalculationWriterA {

	@Autowired
	private CalcJobRepository jobRepository;
	@Autowired
	private AssetDataLogRepository assetDataLogRepository;

	public void write(SliceResponseDTO items) {

		SliceResponseDTO apiResp = items;
		try {
			List<AssetDataLog> aList = assetDataLogRepository.findByAssetName(apiResp.getId(), apiResp.getAssetId(),
					ApplicationConstants.STATUS_DONE, apiResp.getTenantId());
			if (!CollectionUtils.isEmpty(apiResp.getJobs()) && !Objects.isNull(apiResp.getAssetId())) {

				jobRepository.saveAll(apiResp.getJobs());
				updateStatus(aList, ApplicationConstants.STATUS_CALC_IN_PROGRESS);

			} else {

				if (!apiResp.isRecordSame()) {

					updateStatus(aList, ApplicationConstants.STATUS_FAILED);
				}

			}
		} catch (Exception e) {
			log.info("error in CalculationWriterA {}", e);
		}

	}

	private void updateStatus(List<AssetDataLog> aList, String status) {
		aList.forEach(assetLog -> {
			assetLog.setWorkFlowStatus(status);
			assetLog.setCalcStartTime(new Date());
			assetDataLogRepository.save(assetLog);
		});
	}
}
