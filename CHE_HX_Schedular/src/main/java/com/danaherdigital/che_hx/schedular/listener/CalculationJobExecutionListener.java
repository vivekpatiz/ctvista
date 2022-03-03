package com.danaherdigital.che_hx.schedular.listener;


import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CalculationJobExecutionListener implements JobExecutionListener {



    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("jobId:{}",jobExecution.getId());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("afterJob: " + jobExecution.getStatus());


    }
}