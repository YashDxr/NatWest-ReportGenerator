package com.yash.natwestassignmentreportgenerator.Services;

import com.yash.natwestassignmentreportgenerator.Models.InputData;
import com.yash.natwestassignmentreportgenerator.Models.ReferenceData;
import com.yash.natwestassignmentreportgenerator.Models.ReportData;
import com.yash.natwestassignmentreportgenerator.Repositories.InputDataRepository;
import com.yash.natwestassignmentreportgenerator.Repositories.ReferenceDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReportGenerationService {

    @Autowired
    private InputDataRepository inputDataRepository;

    @Autowired
    private ReferenceDataRepository referenceDataRepository;


    public List<ReportData> generateReport() {

        List<InputData> inputDataList = inputDataRepository.findAll();
        System.out.println(inputDataList);

        List<String> refkeys = inputDataList.stream()
                .map(InputData::getRefkey1)
                .distinct()
                .collect(Collectors.toList());

        List<ReferenceData> referenceDataList = referenceDataRepository.findByRefkey1In(refkeys);
        Map<String, ReferenceData> referenceDataMap = referenceDataList.stream()
                .collect(Collectors.toConcurrentMap(ReferenceData::getRefkey1, referenceData -> referenceData));

        List<ReportData> reportDataList = inputDataList.parallelStream()
                .map(inputData -> {
                    ReferenceData referenceData = referenceDataMap.get(inputData.getRefkey1());
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
        return inputDataRepository.findAll();
    }

    public List<ReferenceData> findReferenceRecords() {
        return referenceDataRepository.findAll();
    }



}
