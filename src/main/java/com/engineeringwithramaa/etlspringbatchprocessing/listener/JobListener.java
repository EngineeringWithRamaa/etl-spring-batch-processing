package com.engineeringwithramaa.etlspringbatchprocessing.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        final String jobName = jobExecution.getJobInstance().getJobName();
        final BatchStatus jobStatus = jobExecution.getStatus();

        System.out.println("JobListener :: beforeJob() -> JobExecution " +
                            jobName + ", " + jobStatus.getBatchStatus());

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        final String jobName = jobExecution.getJobInstance().getJobName();
        final BatchStatus jobStatus = jobExecution.getStatus();

        System.out.println("JobListener :: afterJob() -> jobExecution: " +
                            jobName + ", " + jobStatus.getBatchStatus());
    }
}
