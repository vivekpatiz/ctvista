package com.danaherdigital.che_hx.schedular.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.danaherdigital.che_hx.schedular.dto.SliceDTO;
import com.danaherdigital.che_hx.schedular.dto.SliceResponseDTO;
import com.danaherdigital.che_hx.schedular.model.AssetDataLog;
import com.danaherdigital.che_hx.schedular.model.CalcJob;
import com.danaherdigital.che_hx.schedular.repo.AssetDataRespository;
import com.danaherdigital.che_hx.schedular.repo.CalcJobRepository;
import com.danaherdigital.che_hx.schedular.utils.ApplicationConstants;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

import java.util.List;
import java.util.Objects;


@Slf4j
@Service
public class CalculationProcessorA {

	@Autowired
	private AssetDataRespository assetDataRepository;
	@Autowired
	private CalcJobRepository calcJobRepository;

	@Value("${calculation.engine.chunk.size}")
	Long chunk;

	public SliceResponseDTO process(AssetDataLog assetDataLog) {

		SliceResponseDTO respDTO = new SliceResponseDTO();
		if (!Objects.isNull(assetDataLog)) {
			respDTO.setAssetId(assetDataLog.getAssetName());
			respDTO.setId(assetDataLog.getId());
			respDTO.setTenantId(assetDataLog.getTenantId());
			try {
				// fetch from timeseries service-----tentative

				respDTO.setAssetId(assetDataLog.getAssetName());

				List<CalcJob> jobList = new ArrayList<>();
				// if row count is less than or equal to chunk size

				// if row count is greater than chunk size

				SliceDTO timeStamps = assetDataRepository.getDataTimeStamp(assetDataLog.getStartDate(),
						assetDataLog.getEndDate(), assetDataLog.getAssetName(), assetDataLog.getTenantId());

				log.info("min timstamps:{} and maxTime:{}", timeStamps.getMinTime(), timeStamps.getMaxTime());

				boolean isExists = calcJobRepository.findByStatusAndAssetName(assetDataLog.getAssetName(),
						ApplicationConstants.STATUS_READY, assetDataLog.getTenantId(),timeStamps.getMinTime(),timeStamps.getMaxTime());
				if (!(isExists)) {
					CalcJob job = createJobDO(timeStamps, assetDataLog);
					if (!Objects.isNull(job)) {
						jobList.add(job);
					}

					// batch insert to job table
					if (!CollectionUtils.isEmpty(jobList)) {

						respDTO.setJobs(jobList);
					}
				} else {
					respDTO.setRecordSame(true);
				}

			} catch (Exception e) {
				log.error("error in CalculationProcessor.process->{}", e);
			}

		}
		return respDTO;
	}

	private CalcJob createJobDO(SliceDTO timeStamps, AssetDataLog assetDataLog) {
		CalcJob job = null;

		if (!Objects.isNull(timeStamps) && !Objects.isNull(timeStamps.getMinTime())
				&& !Objects.isNull(timeStamps.getMaxTime())) {
			job = new CalcJob();

			job.setFromDate(timeStamps.getMinTime());

			// this should be from new config table
			job.setEngine(ApplicationConstants.ENGINE);
			job.setMethod(ApplicationConstants.FUNCTION);
			job.setToDate(timeStamps.getMaxTime());
			job.setStatus(ApplicationConstants.STATUS_READY);
			job.setAssetName(assetDataLog.getAssetName());
			job.setTenantId(assetDataLog.getTenantId());
			job.setInflowId(assetDataLog.getId());

		}
		return job;

	}

}
