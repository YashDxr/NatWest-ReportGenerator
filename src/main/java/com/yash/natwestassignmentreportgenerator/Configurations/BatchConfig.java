package com.yash.natwestassignmentreportgenerator.Configurations;

import com.yash.natwestassignmentreportgenerator.Batch.CsvFileItemReader;
import com.yash.natwestassignmentreportgenerator.Models.InputData;
import com.yash.natwestassignmentreportgenerator.Models.OutputData;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {



    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private CsvFileItemReader csvFileItemReader;

    @Autowired
    private ItemProcessor<InputData, OutputData> reportItemProcessor;

    @Autowired
    private ItemWriter<OutputData> csvFileItemWriter;

    @Bean
    public Job generateReportJob() {
        return new JobBuilder("generateReportJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(generateReportStep())
                .build();
    }

    @Bean
    public Step generateReportStep() {
        return new StepBuilder("generateReportStep", jobRepository)
                .<InputData, OutputData>chunk(10, transactionManager)
                .reader(csvFileItemReader)
                .processor(reportItemProcessor)
                .writer(csvFileItemWriter)
                .build();
    }
}

