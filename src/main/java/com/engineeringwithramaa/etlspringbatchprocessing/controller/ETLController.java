package com.engineeringwithramaa.etlspringbatchprocessing.controller;

import com.engineeringwithramaa.etlspringbatchprocessing.DTO.BatchStatusDTO;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/etl")
public class ETLController {
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

    @Scheduled(cron = "0 45 16 28 * *")
    public void scheduleByFixedRate() throws Exception {
        System.out.println("***** BATCH PROCESSING STARTS *****");
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("time", format.format(Calendar.getInstance().getTime())).toJobParameters();
        jobLauncher.run(job, jobParameters);
        System.out.println("***** EXECUTION OVER ******\n");
    }
    /*
    @GetMapping("/start-batch")
    public ResponseEntity<BatchStatusDTO> startTheJobLauncher()
            throws JobInstanceAlreadyCompleteException,
            JobExecutionAlreadyRunningException,
            JobParametersInvalidException, JobRestartException {
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("Time-Stamp", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(maps);

        JobExecution execution = jobLauncher.run(job1, jobParameters);

        Date endTime = execution.getEndTime();
        Date startTime = execution.getStartTime();
        long durationTime = ((endTime.getTime() - startTime.getTime())/1000) % 60;


        BatchStatusDTO userJobStatus = new BatchStatusDTO(execution.getJobId(),
                                    job1.getName(),
                                    execution.getStartTime(),
                                    execution.getEndTime(),
                   String.valueOf(durationTime)+"s",
                                    execution.getStatus().toString());

        return ResponseEntity.ok(userJobStatus);
    }

    */

}
