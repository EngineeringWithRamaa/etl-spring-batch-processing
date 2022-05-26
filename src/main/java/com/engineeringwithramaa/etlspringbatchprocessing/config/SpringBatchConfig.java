package com.engineeringwithramaa.etlspringbatchprocessing.config;

import com.engineeringwithramaa.etlspringbatchprocessing.entity.ECT;
import com.engineeringwithramaa.etlspringbatchprocessing.entity.LibraryRecord;
import com.engineeringwithramaa.etlspringbatchprocessing.entity.User;
import com.engineeringwithramaa.etlspringbatchprocessing.listener.JobListener;
import com.engineeringwithramaa.etlspringbatchprocessing.listener.ReadListener;
import com.engineeringwithramaa.etlspringbatchprocessing.listener.WriteListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

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
    JobListener jobExecListener;

    @Bean
    public Step userStep(){
        return stepBuilderFactory.get("user-step")
                .listener(new com.engineeringwithramaa.etlspringbatchprocessing.listener.StepListener())
                .<User, User>chunk(100)
                .reader(reader)
                .listener(userReadListener)
                .processor(processor)
                .writer(writer)
                .listener(userWriteListener)
                .build();
    }

    @Bean
    public Step ECTStep() {
        return stepBuilderFactory.get("electronic-card-transaction-step")
                .listener(new com.engineeringwithramaa.etlspringbatchprocessing.listener.StepListener())
                .<ECT, ECT>chunk(200)
                .reader(ECTReader)
                .processor(ECTProcessor)
                .writer(ECTWriter)
                .build();
    }

    @Bean
    public Step libraryRecordStep() {
        return stepBuilderFactory.get("library-record-step")
                .listener(new com.engineeringwithramaa.etlspringbatchprocessing.listener.StepListener())
                .<LibraryRecord, LibraryRecord>chunk(200)
                .reader(lrReader)
                .processor(lrProcessor)
                .writer(lrWriter)
                .build();
    }

    @Bean
	public Flow userAndECTSteps() {

		FlowBuilder<Flow> flowBuilder = new FlowBuilder<Flow>
                                    ("Flow 2 - User Step & Electronic Card Transaction");
		flowBuilder.start(userStep()).next(ECTStep()).end();
		return flowBuilder.build();
	}

    @Bean
    public Flow libraryRecordsFlow() {
        return new FlowBuilder<Flow>("Flow 1 - Library Records  ")
                .start(libraryRecordStep())
                .build();
    }

    @Bean
    public Flow splitFlow(){
        return new FlowBuilder<Flow>("Split Flow")
                .split(new SimpleAsyncTaskExecutor())
                .add(libraryRecordsFlow(), userAndECTSteps())
                .build();
    }

    @Bean
    public Job userTableTransformationJob() {
        return jobBuilderFactory.get("ETL Batch Processing - Job ")
                .listener(jobExecListener)
                .incrementer(new RunIdIncrementer())
                .start(libraryRecordsFlow())
                .split(new SimpleAsyncTaskExecutor("Parallel Steps - Simple Async Task Executor"))
                .add(splitFlow())
                .end()
                .build();

    }

}
