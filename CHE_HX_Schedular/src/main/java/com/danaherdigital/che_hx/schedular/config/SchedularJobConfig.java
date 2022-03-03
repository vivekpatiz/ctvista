package com.danaherdigital.che_hx.schedular.config;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import com.danaherdigital.che_hx.schedular.dto.ApiResponseDTO;
import com.danaherdigital.che_hx.schedular.job.CalculationProcessorB;
import com.danaherdigital.che_hx.schedular.job.CalculationReaderB;
import com.danaherdigital.che_hx.schedular.job.CalculationWriterB;
import com.danaherdigital.che_hx.schedular.listener.CalculationJobExecutionListener;
import com.danaherdigital.che_hx.schedular.listener.CalculationProcessorListener;
import com.danaherdigital.che_hx.schedular.listener.CalculationReaderListener;
import com.danaherdigital.che_hx.schedular.listener.CalculationWriterListener;
import com.danaherdigital.che_hx.schedular.model.CalcJob;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableBatchProcessing
@EnableScheduling
@Slf4j
public class SchedularJobConfig {

	@Autowired
	private Job calculationManagerJob;
	@Autowired
	private JobLauncher jobLauncher;
	@Value("${schedular.job.restrict}")
	int restrictCount;

	@Bean
	public CalculationReaderB readerb() {
		return new CalculationReaderB();
	}

	@Bean
	public CalculationProcessorB processorb() {
		return new CalculationProcessorB();
	}

	@Bean
	public CalculationWriterB writerb() {
		return new CalculationWriterB();
	}

	@Bean
	public CalculationJobExecutionListener jobExecutionListener() {
		return new CalculationJobExecutionListener();
	}

	@Bean
	public CalculationReaderListener readerListener() {
		return new CalculationReaderListener();
	}

	@Bean
	public CalculationProcessorListener itemProcessListener() {
		return new CalculationProcessorListener();
	}

	@Bean
	public CalculationWriterListener writerListener() {
		return new CalculationWriterListener();
	}


	@Bean
	public TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(20);
		executor.setMaxPoolSize(20);
		executor.setQueueCapacity(20);
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.setThreadNamePrefix("CHE_HX-Schedular-");
		executor.afterPropertiesSet();
		return executor;
	}

	@Bean
	public Step secondStep(CalculationReaderB reader, CalculationProcessorB processor, CalculationWriterB writer,

			CalculationReaderListener readerListener, CalculationProcessorListener calculationProcessorListener,
			CalculationWriterListener writerListener,

			StepBuilderFactory stepBuilderFactory) {
		return stepBuilderFactory.get("secondStep").<CalcJob, ApiResponseDTO>chunk(restrictCount).reader(reader)
				.processor(processor).writer(writer).faultTolerant().skip(Exception.class).skipLimit(2)
				.taskExecutor(taskExecutor()).throttleLimit(20)

				.listener(readerListener).listener(calculationProcessorListener).listener(writerListener).build();

	}

	/**
	 * Creates a bean that represents our example batch job.
	 *
	 * @param exampleJobStep
	 * @param jobBuilderFactory
	 * @return
	 */
	@Bean
	public Job calculationManagerJob(Step firstStep, Step secondStep, JobBuilderFactory jobBuilderFactory) {
		return jobBuilderFactory.get("calculationManagerJob").listener(new CalculationJobExecutionListener())
				 .preventRestart()
				.incrementer(new RunIdIncrementer()).start(secondStep)
				// .next(secondStep)

				.build();
	}

	@Scheduled(fixedRateString = "${schedular.config.fixedRate}", initialDelay = 0)
	public void runJob() {
		try {
			long start = System.currentTimeMillis();

			JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
					.addLong("jobId", System.currentTimeMillis()).toJobParameters();

			log.info("calculation manager job is started at:{}", start);
			jobLauncher.run(calculationManagerJob, jobParameters);

			long end = System.currentTimeMillis();

			long seconds = TimeUnit.MILLISECONDS.toSeconds(end - start);
			log.info("calculation manager job is completed at:{}", end);
			log.info("total time taken in seconds:{}", seconds);

		} catch (Exception e) {
			log.error("error in SchedularJobConfig.runJob->{}", e);
		}
	}
}