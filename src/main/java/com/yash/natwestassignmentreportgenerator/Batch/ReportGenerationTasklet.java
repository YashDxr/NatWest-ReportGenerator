package com.yash.natwestassignmentreportgenerator.Batch;

import com.yash.natwestassignmentreportgenerator.Models.InputData;
import com.yash.natwestassignmentreportgenerator.Models.OutputData;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ReportGenerationTasklet implements ItemProcessor<InputData, OutputData> {

    @Override
    public OutputData process(InputData inputData) throws Exception {
        OutputData outputData = new OutputData();
        // Apply transformation rules here
        outputData.setOutfield1(inputData.getField1() + inputData.getField2());
        outputData.setOutfield2("Placeholder"); // Example: Set some static value
        outputData.setOutfield3("Placeholder"); // Example: Set some static value
        outputData.setOutfield4(inputData.getField3() + Math.max(inputData.getField5(), 0)); // Example: Adjust transformation logic
        outputData.setOutfield5(Math.max(inputData.getField5(), 0)); // Example: Adjust transformation logic
        return outputData;
    }
}

