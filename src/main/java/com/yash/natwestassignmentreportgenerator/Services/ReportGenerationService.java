package com.yash.natwestassignmentreportgenerator.Services;

import com.yash.natwestassignmentreportgenerator.Controllers.FileUploadController;
import com.yash.natwestassignmentreportgenerator.Models.InputData;
import com.yash.natwestassignmentreportgenerator.Models.ReferenceData;
import com.yash.natwestassignmentreportgenerator.Models.ReportData;
import com.yash.natwestassignmentreportgenerator.Repositories.InputDataRepository;
import com.yash.natwestassignmentreportgenerator.Repositories.ReferenceDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class ReportGenerationService {

    private static final Logger logger = LoggerFactory.getLogger(ReportGenerationService.class);

    @Autowired
    private InputDataRepository inputDataRepository;

    @Autowired
    private ReferenceDataRepository referenceDataRepository;


    public List<ReportData> generateReport() {
        logger.info("Generating report");
        List<InputData> inputDataList = inputDataRepository.findAll();
        System.out.println(inputDataList);

        List<String> refkey1List = inputDataList.stream()
                .map(InputData::getRefkey1)
                .distinct()
                .collect(Collectors.toList());

        List<String> refkey2List = inputDataList.stream()
                .map(InputData::getRefkey2)
                .distinct()
                .collect(Collectors.toList());

        List<ReferenceData> referenceDataList = referenceDataRepository.findByRefkey1InOrRefkey2In(refkey1List, refkey2List);
        Map<String, ReferenceData> referenceDataMap = new ConcurrentHashMap<>();

        logger.info("Generating reference data");
        for (ReferenceData referenceData : referenceDataList) {
            referenceDataMap.put(referenceData.getRefkey1() + "|" + referenceData.getRefkey2(), referenceData);
        }

        List<ReportData> reportDataList = inputDataList.parallelStream()
                .map(inputData -> {
                    ReferenceData referenceData = referenceDataMap.get(inputData.getRefkey1() + "|" + inputData.getRefkey2());
                    if (referenceData == null) {
                        return null;
                    }

                    ReportData report = new ReportData();
                    report.setOutfield1(inputData.getField1() + inputData.getField2());
                    report.setOutfield2(referenceData.getRefdata1());
                    report.setOutfield3(referenceData.getRefdata2() + referenceData.getRefdata3());
                    report.setOutfield4(inputData.getField3() + Math.max(inputData.getField5(), referenceData.getRefdata4()));
                    report.setOutfield5(Math.max(inputData.getField5(), referenceData.getRefdata4()));

                    return report;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return reportDataList;
    }

    public List<InputData> findInputRecords() {
        logger.info("Finding input records");
        return inputDataRepository.findAll();
    }

    public List<ReferenceData> findReferenceRecords() {
        logger.info("Finding reference records");
        return referenceDataRepository.findAll();
    }



}
