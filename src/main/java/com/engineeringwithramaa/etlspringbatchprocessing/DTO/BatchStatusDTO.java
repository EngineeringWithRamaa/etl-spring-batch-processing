package com.engineeringwithramaa.etlspringbatchprocessing.DTO;

import java.util.Date;

public class BatchStatusDTO {

    private long jobId;
    private String jobName;
    private Date jobStartTime;
    private Date jobExitTime;
    private String jobExecutionTime;
    private String jobExectionStatus;

    public BatchStatusDTO() {
    }

    public BatchStatusDTO(long jobId,
                          String jobName,
                          Date jobStartTime,
                          Date jobExitTime,
                          String jobExecutionTime,
                          String jobExectionStatus) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.jobStartTime = jobStartTime;
        this.jobExitTime = jobExitTime;
        this.jobExecutionTime = jobExecutionTime;
        this.jobExectionStatus = jobExectionStatus;
    }

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Date getJobStartTime() {
        return jobStartTime;
    }

    public void setJobStartTime(Date jobStartTime) {
        this.jobStartTime = jobStartTime;
    }

    public Date getJobExitTime() {
        return jobExitTime;
    }

    public void setJobExitTime(Date jobExitTime) {
        this.jobExitTime = jobExitTime;
    }

    public String getJobExecutionTime() {
        return jobExecutionTime;
    }

    public void setJobExecutionTime(String jobExecutionTime) {
        this.jobExecutionTime = jobExecutionTime;
    }

    public String getJobExectionStatus() {
        return jobExectionStatus;
    }

    public void setJobExectionStatus(String jobExectionStatus) {
        this.jobExectionStatus = jobExectionStatus;
    }

    @Override
    public String toString() {
        return "BatchStatusDTO{" +
                "jobId=" + jobId +
                ", jobStartTime='" + jobStartTime + '\'' +
                ", jobExitTime='" + jobExitTime + '\'' +
                ", jobExecutionTime='" + jobExecutionTime + '\'' +
                ", jobExectionStatus='" + jobExectionStatus + '\'' +
                '}';
    }
}
