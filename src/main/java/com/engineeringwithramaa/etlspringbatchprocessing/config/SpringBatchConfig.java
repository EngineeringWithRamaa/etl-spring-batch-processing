package com.engineeringwithramaa.etlspringbatchprocessing.config;

import com.engineeringwithramaa.etlspringbatchprocessing.batch.ECTReader;
import com.engineeringwithramaa.etlspringbatchprocessing.batch.LibraryRecordReader;
import com.engineeringwithramaa.etlspringbatchprocessing.batch.LibraryRecordWriter;
import com.engineeringwithramaa.etlspringbatchprocessing.batch.UserCSVReader;
import com.engineeringwithramaa.etlspringbatchprocessing.entity.ECT;
import com.engineeringwithramaa.etlspringbatchprocessing.entity.LibraryRecord;
import com.engineeringwithramaa.etlspringbatchprocessing.entity.User;
import com.engineeringwithramaa.etlspringbatchprocessing.listener.JobListener;
import com.engineeringwithramaa.etlspringbatchprocessing.listener.ReadListener;
import com.engineeringwithramaa.etlspringbatchprocessing.listener.WriteListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepListener;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Autowired
    StepBuilderFactory stepBuilderFactory;
    @Autowired
    ItemReader<User> reader;
    @Autowired
    ItemProcessor<User, User> processor;
    @Autowired
    ItemWriter<User> writer;
    @Autowired
    ItemReader<ECT> ECTReader;
    @Autowired
    ItemProcessor<ECT, ECT> ECTProcessor;
    @Autowired
    ItemWriter<ECT> ECTWriter;
    @Autowired
    ItemReader<LibraryRecord> lrReader;
    @Autowired
    ItemProcessor<LibraryRecord, LibraryRecord> lrProcessor;
    @Autowired
    ItemWriter<LibraryRecord> lrWriter;
    @Autowired
    ReadListener<User> userReadListener;
    @Autowired
    WriteListener<User> userWriteListener;
    @Autowired
    StepListener stepListener;
    @Autowired
    JobListener jobExecListener;

    @Bean
    public Job userTableTransformationJob() {

        Step userStep = stepBuilderFactory.get("user-step")
                .<User, User>chunk(100)
                .reader(reader)
                .listener(userReadListener)
                .processor(processor)
                .writer(writer)
                .listener(userWriteListener)
                .listener(stepListener)
                .build();

        Step ECTStep = stepBuilderFactory.get("electronic-card-transaction-step")
                .<ECT, ECT>chunk(200)
                .reader(ECTReader)
                .processor(ECTProcessor)
                .writer(ECTWriter)
                .listener(stepListener)
                .build();

        Step libraryRecordStep = stepBuilderFactory.get("library-record-step")
                .<LibraryRecord, LibraryRecord>chunk(200)
                .reader(lrReader)
                .processor(lrProcessor)
                .writer(lrWriter)
                .build();


        return jobBuilderFactory.get("etl-batch-job")
                .listener(jobExecListener)
                .incrementer(new RunIdIncrementer())
                .start(userStep)
                .next(ECTStep)
                //.next(libraryRecordStep)
                .build();

    }

}
