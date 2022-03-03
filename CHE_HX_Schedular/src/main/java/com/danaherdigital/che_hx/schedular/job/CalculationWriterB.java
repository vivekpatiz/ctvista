package com.danaherdigital.che_hx.schedular.job;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.danaherdigital.che_hx.schedular.dto.ApiResponseDTO;
import com.danaherdigital.che_hx.schedular.dto.PersistResDTO;
import com.danaherdigital.che_hx.schedular.dto.TimeSeriesReqDTO;
import com.danaherdigital.che_hx.schedular.model.AssetDataLog;
import com.danaherdigital.che_hx.schedular.model.CalcJob;
import com.danaherdigital.che_hx.schedular.repo.AssetDataLogRepository;
import com.danaherdigital.che_hx.schedular.repo.CalcJobRepository;
import com.danaherdigital.che_hx.schedular.service.ITimeSeriesService;
import com.danaherdigital.che_hx.schedular.utils.ApplicationConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CalculationWriterB implements ItemWriter<ApiResponseDTO> {
	@Autowired
	ITimeSeriesService timeSeriesService;
	@Autowired
	private CalcJobRepository jobRepository;
	@Autowired
	private AssetDataLogRepository assetDataLogRepository;

	@Override
	public void write(List<? extends ApiResponseDTO> items) throws Exception {

		if (!Objects.isNull(items)) {
			ApiResponseDTO apiResp = items.get(0);
			TimeSeriesReqDTO dto = new TimeSeriesReqDTO();
			dto.setAssetId(apiResp.getAssetId());
			dto.setTenantId(apiResp.getTenantId());

			try {
				List<AssetDataLog> aList = assetDataLogRepository.findByAssetName(apiResp.getId(), apiResp.getAssetId(),
						ApplicationConstants.STATUS_CALC_IN_PROGRESS, apiResp.getTenantId());
				if (!Objects.isNull(apiResp.getResult()) || !CollectionUtils.isEmpty(apiResp.getResult())) {
					dto.setInput(apiResp.getResult());

					PersistResDTO resp = timeSeriesService.persistTimeSeriesData(dto);
					if (resp.isPersisted()) {
						log.info("Data persisted successfully");

						updateJobStatus(apiResp.getJobId(), ApplicationConstants.STATUS_COMPLETED);
						updateInflowLogStatus(aList, ApplicationConstants.STATUS_CALC_COMPLTED);

					} else {
						if (resp.isDeadLock()) {
							updateJobStatus(apiResp.getJobId(), ApplicationConstants.STATUS_FAILED);
							updateInflowLogStatus(aList, ApplicationConstants.STATUS_DONE);
						} else {
							updateInflowLogStatus(aList, ApplicationConstants.STATUS_FAILED);
						}
					}
				} else {
					log.info("timeseries persist error");

					updateJobStatus(apiResp.getJobId(), ApplicationConstants.STATUS_FAILED);
					updateInflowLogStatus(aList, ApplicationConstants.STATUS_FAILED);

				}

			} catch (Exception e) {
				log.info("Error in writerB...{}", e);
			}

		}

	}

	private void updateInflowLogStatus(List<AssetDataLog> aList, String status) {
		aList.forEach(assetLog -> {
			
			assetLog.setWorkFlowStatus(status);
			if(status.equalsIgnoreCase(ApplicationConstants.STATUS_CALC_COMPLTED)) {
				assetLog.setCalcEndTime(new Date());
			}
			else if(status.equalsIgnoreCase(ApplicationConstants.STATUS_FAILED)) {
				assetLog.setCalcFailedTime(new Date());
			}
			else if(status.equalsIgnoreCase(ApplicationConstants.STATUS_CALC_IN_PROGRESS)) {
				assetLog.setCalcStartTime(new Date());
			}
			assetDataLogRepository.save(assetLog);
		});
	}

	private void updateJobStatus(String jobId, String status) {
		Optional<CalcJob> caljob = jobRepository.findById(jobId);
		if (caljob.isPresent()) {
			caljob.get().setStatus(status);
			jobRepository.save(caljob.get());
		}
	}
}
