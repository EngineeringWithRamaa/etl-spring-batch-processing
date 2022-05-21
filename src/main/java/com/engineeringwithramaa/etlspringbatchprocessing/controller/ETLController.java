package com.engineeringwithramaa.etlspringbatchprocessing.controller;

import com.engineeringwithramaa.etlspringbatchprocessing.DTO.BatchStatusDTO;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/start-batch")
public class ETLController {
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;

    @GetMapping
    public ResponseEntity<BatchStatusDTO> startTheJobLauncher() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("Time-Stamp", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(maps);
        JobExecution execution = jobLauncher.run(job, jobParameters);

        Date endTime = execution.getEndTime();
        Date startTime = execution.getStartTime();
        long durationTime = ((endTime.getTime() - startTime.getTime())/1000) % 60;


        BatchStatusDTO jobStatus = new BatchStatusDTO(execution.getJobId(),
                                    job.getName(),
                                    execution.getStartTime(),
                                    execution.getEndTime(),
                                    String.valueOf(durationTime)+"s",
                                    execution.getStatus().toString());

        return ResponseEntity.ok(jobStatus);
    }
}
