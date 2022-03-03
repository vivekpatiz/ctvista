package com.danaherdigital.che_hx.schedular.job;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;

import com.danaherdigital.che_hx.schedular.dto.SliceResponseDTO;
import com.danaherdigital.che_hx.schedular.model.AssetDataLog;
import com.danaherdigital.che_hx.schedular.model.CalcJob;
import com.danaherdigital.che_hx.schedular.repo.AssetDataLogRepository;
import com.danaherdigital.che_hx.schedular.repo.CalcJobRepository;
import com.danaherdigital.che_hx.schedular.utils.ApplicationConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CalculationReaderB implements ItemReader<CalcJob> {
	@Autowired
	private CalcJobRepository jobRepository;
	private Iterator<CalcJob> calcJobIterator;
	@Autowired
	private AssetDataLogRepository assetDataLogRepository;
	@Value("${schedular.job.restrict}")
	int restrictCount;
	@Autowired
	CalculationProcessorA calculationProcessorA;
	@Autowired
	CalculationWriterA calculationWriterA;

	@BeforeStep
	public void before(StepExecution stepExecution) {

		try {
			List<AssetDataLog> assetDataLog = assetDataLogRepository
					.findByworkFlowStatus(ApplicationConstants.STATUS_DONE, PageRequest.of(0, restrictCount));
			for (AssetDataLog adl : assetDataLog) {
				SliceResponseDTO resp = calculationProcessorA.process(adl);
				if (!Objects.isNull(resp) && !CollectionUtils.isEmpty(resp.getJobs())) {

					calculationWriterA.write(resp);

				}

			}
			List<CalcJob> calcJobList = jobRepository.findByStatus(ApplicationConstants.STATUS_READY);
			calcJobIterator = calcJobList.iterator();
		} catch (Exception e) {
			log.info("error in CalculationReaderB.before:{}", e);
		}

	}

	@Override
	public CalcJob read() throws Exception {
		if (calcJobIterator != null && calcJobIterator.hasNext()) {
			Optional<CalcJob> job = jobRepository.findById(calcJobIterator.next().getId());

			if (job.isPresent()) {

				job.get().setStatus("In Progress");


				return jobRepository.save(job.get());
			}
		}

		return null;
	}

}
