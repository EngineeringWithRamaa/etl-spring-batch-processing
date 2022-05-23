package com.engineeringwithramaa.etlspringbatchprocessing.config;

import com.engineeringwithramaa.etlspringbatchprocessing.batch.UserCSVReader;
import com.engineeringwithramaa.etlspringbatchprocessing.entity.ECT;
import com.engineeringwithramaa.etlspringbatchprocessing.entity.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
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
    private UserCSVReader reader;

    @Bean
    public Job userTableTransformationJob(JobBuilderFactory jobBuilderFactory,
                                          StepBuilderFactory stepBuilderFactory,
                                          ItemReader<User> reader,
                                          ItemProcessor<User, User> processor,
                                          ItemWriter<User> writer,
                                          ItemReader<ECT> ECTReader,
                                          ItemProcessor<ECT, ECT> ECTProcessor,
                                          ItemWriter<ECT> ECTWriter) {

        Step userStep = stepBuilderFactory.get("etl-user-step")
                .<User, User>chunk(100)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();

        Step ECTStep = stepBuilderFactory.get("etl-electronic-card-transaction-step")
                .<ECT, ECT>chunk(200)
                .reader(ECTReader)
                .processor(ECTProcessor)
                .writer(ECTWriter)
                .build();


        return jobBuilderFactory.get("etl-batch-job")
                .incrementer(new RunIdIncrementer())
                .start(userStep)
                .next(ECTStep)
                .build();

    }

}
