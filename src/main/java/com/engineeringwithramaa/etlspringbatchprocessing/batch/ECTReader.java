package com.engineeringwithramaa.etlspringbatchprocessing.batch;

import com.engineeringwithramaa.etlspringbatchprocessing.entity.ECT;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ECTReader {
    @Bean
    public FlatFileItemReader<ECT> readerECT(@Value("${input2}") Resource resource) throws IOException {
        FlatFileItemReader<ECT> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(resource);
        System.out.println("File Resource " + resource.getFile());
        flatFileItemReader.setName("ECT-csv-reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapperECT());

        return flatFileItemReader;
    }

    @Bean
    public LineMapper<ECT> lineMapperECT() {
        DefaultLineMapper<ECT> defaultLineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        BeanWrapperFieldSetMapper<ECT> fieldSetMapper = new BeanWrapperFieldSetMapper<>();

        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[] {"transactionId", "reference", "period",
                "transactionStatus", "units", "magnitude", "subject", "transactionGroup", "seriesTitle1", "seriesTitle2"});

        fieldSetMapper.setTargetType(ECT.class);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        defaultLineMapper.setLineTokenizer(lineTokenizer);

        return defaultLineMapper;

    }

}
