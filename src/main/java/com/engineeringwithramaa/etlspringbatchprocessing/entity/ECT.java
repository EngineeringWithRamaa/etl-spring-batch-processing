package com.engineeringwithramaa.etlspringbatchprocessing.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ect")
public class ECT {
    @Id
    private String referenceId;
    private String transactionDate;
    private String transactionSatus;
    private String transactionUnits;
    private int magnitude;
    private String transactionSubject;
    private String transactionGroup;
    private String title1;
    private String title2;

    public ECT(String referenceId) {
        this.referenceId = referenceId;
    }

    public ECT(String referenceId, String transactionDate,
               String transactionSatus, String transactionUnits,
               int magnitude, String transactionSubject,
               String transactionGroup, String title1, String title2) {
        this.referenceId = referenceId;
        this.transactionDate = transactionDate;
        this.transactionSatus = transactionSatus;
        this.transactionUnits = transactionUnits;
        this.magnitude = magnitude;
        this.transactionSubject = transactionSubject;
        this.transactionGroup = transactionGroup;
        this.title1 = title1;
        this.title2 = title2;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionSatus() {
        return transactionSatus;
    }

    public void setTransactionSatus(String transactionSatus) {
        this.transactionSatus = transactionSatus;
    }

    public String getTransactionUnits() {
        return transactionUnits;
    }

    public void setTransactionUnits(String transactionUnits) {
        this.transactionUnits = transactionUnits;
    }

    public int getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(int magnitude) {
        this.magnitude = magnitude;
    }

    public String getTransactionSubject() {
        return transactionSubject;
    }

    public void setTransactionSubject(String transactionSubject) {
        this.transactionSubject = transactionSubject;
    }

    public String getTransactionGroup() {
        return transactionGroup;
    }

    public void setTransactionGroup(String transactionGroup) {
        this.transactionGroup = transactionGroup;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    @Override
    public String toString() {
        return "ECT{" +
                "referenceId='" + referenceId + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                ", transactionSatus='" + transactionSatus + '\'' +
                ", transactionUnits='" + transactionUnits + '\'' +
                ", magnitude=" + magnitude +
                ", transactionSubject='" + transactionSubject + '\'' +
                ", transactionGroup='" + transactionGroup + '\'' +
                ", title1='" + title1 + '\'' +
                ", title2='" + title2 + '\'' +
                '}';
    }
}
