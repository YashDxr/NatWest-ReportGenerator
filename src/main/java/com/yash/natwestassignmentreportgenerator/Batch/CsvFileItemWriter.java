package com.yash.natwestassignmentreportgenerator.Batch;


import com.yash.natwestassignmentreportgenerator.Models.OutputData;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CsvFileItemWriter extends FlatFileItemWriter<OutputData> {

    public CsvFileItemWriter() {
        this.setResource(new FileSystemResource("../Files/output.csv")); // Specify your output file path here
        this.setAppendAllowed(true); // Append to existing file if needed

        DelimitedLineAggregator<OutputData> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(",");
        lineAggregator.setFieldExtractor(outputData -> new String[]{
                outputData.getOutfield1(),
                outputData.getOutfield2(),
                outputData.getOutfield3(),
                outputData.getOutfield4(),
                String.valueOf(outputData.getOutfield5())
        });

        this.setLineAggregator(lineAggregator);
    }

    public void write(List<? extends OutputData> items) throws Exception {
        for (OutputData item : items) {
            super.write(item);
        }
    }
}
