package com.yash.natwestassignmentreportgenerator.Services;

import com.yash.natwestassignmentreportgenerator.Batch.ReportGenerationTasklet;
import com.yash.natwestassignmentreportgenerator.Models.InputData;
import com.yash.natwestassignmentreportgenerator.Models.OutputData;
import com.yash.natwestassignmentreportgenerator.Models.ReferenceData;
import com.yash.natwestassignmentreportgenerator.Models.ReportData;
import com.yash.natwestassignmentreportgenerator.Repositories.InputDataRepository;
import com.yash.natwestassignmentreportgenerator.Repositories.OutputDataRepository;
import com.yash.natwestassignmentreportgenerator.Repositories.ReferenceDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ScheduledFuture;

@Service
public class ReportGenerationService {

    @Autowired
    private InputDataRepository inputDataRepository;

    @Autowired
    private ReferenceDataRepository referenceDataRepository;

    @Autowired
    private OutputDataRepository outputDataRepository;



    private TaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
    private ScheduledFuture<?> scheduledTask;
    @Autowired
    private ReportGenerationTasklet reportGenerationTasklet;

    public List<ReportData> generateReport() {
        System.out.println("Started Reportservice");
        List<InputData> inputDataList = inputDataRepository.findAll();
        System.out.println("Got inputDataList: " + inputDataList);
//        List<ReferenceData> referenceDataList = referenceDataRepository.findAll();
//        List<OutputData> outputDataList = new ArrayList<>();
        List<ReportData> reportDataList = new ArrayList<>();

//        for (InputData inputData : inputDataList) {
//            Optional<ReferenceData> referenceDataOptional = referenceDataRepository.findByRefkey1AndRefkey2(inputData.getRefkey1(), inputData.getRefkey2());
//            if (referenceDataOptional.isPresent()) {
//                ReferenceData referenceData = referenceDataOptional.get();
//                OutputData outputData = new OutputData();
//                outputData.setOutfield1(inputData.getField1() + inputData.getField2());
//                outputData.setOutfield2(referenceData.getRefdata1());
//                outputData.setOutfield3(referenceData.getRefdata2() + referenceData.getRefdata3());
//                outputData.setOutfield4(inputData.getField3() + Math.max(inputData.getField5(), referenceData.getRefdata4()));
//                outputData.setOutfield5(Math.max(inputData.getField5(), referenceData.getRefdata4()));
//                outputDataList.add(outputData);
//            }
//        }

        for(InputData inputData : inputDataList) {
            ReferenceData referenceData = referenceDataRepository.findByrefkey1(inputData.getRefkey1());
            System.out.println("Got referenceData: " + referenceData);
            ReportData report = new ReportData();

            report.setOutfield1(inputData.getField1()+inputData.getField2());
            report.setOutfield2(referenceData.getRefdata1());
            report.setOutfield3(referenceData.getRefdata2()+referenceData.getRefdata3());
            report.setOutfield4(inputData.getField3() + Math.max(inputData.getField5(), referenceData.getRefdata4()));
            report.setOutfield5(Math.max(inputData.getField5(), referenceData.getRefdata4()));

            reportDataList.add(report);
        }
        System.out.println("Going back to Fileservice");
//        outputDataRepository.saveAll(outputDataList);
        return reportDataList;
    }

    public void scheduleReportGeneration(String cronExpression) {
        if (scheduledTask != null) {
            scheduledTask.cancel(false);
        }

        ((ThreadPoolTaskScheduler) taskScheduler).initialize();
        scheduledTask = taskScheduler.schedule(this::generateReport, Instant.parse(cronExpression));
    }
}

