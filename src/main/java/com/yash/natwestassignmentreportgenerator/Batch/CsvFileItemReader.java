package com.yash.natwestassignmentreportgenerator.Batch;

import com.yash.natwestassignmentreportgenerator.Models.InputData;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class CsvFileItemReader extends FlatFileItemReader<InputData> {

    public CsvFileItemReader() {
        this.setResource(new FileSystemResource("path/to/your/input.csv")); // Specify your input file path here
        this.setLinesToSkip(1); // Skip header line

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(new String[]{"field1", "field2", "field3", "field4", "field5", "refkey1", "refkey2"});
        lineTokenizer.setDelimiter(",");

        BeanWrapperFieldSetMapper<InputData> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(InputData.class);

        DefaultLineMapper<InputData> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        this.setLineMapper(lineMapper);
    }
}

