package com.yash.natwestassignmentreportgenerator.Services;

import com.yash.natwestassignmentreportgenerator.Models.InputData;
import com.yash.natwestassignmentreportgenerator.Models.OutputData;
import com.yash.natwestassignmentreportgenerator.Models.ReferenceData;
import com.yash.natwestassignmentreportgenerator.Models.ReportData;
import com.yash.natwestassignmentreportgenerator.Repositories.InputDataRepository;
import com.yash.natwestassignmentreportgenerator.Repositories.ReferenceDataRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FileProcessingService {

    @Autowired
    private InputDataRepository inputDataRepository;

    @Autowired
    private ReferenceDataRepository referenceDataRepository;


    @Autowired
    ModelMapper mapper;

    @Autowired
    ReportGenerationService reportGenerationService;

    @Transactional
    public List<ReportData> saveFile(MultipartFile inputFile, MultipartFile referenceFile) throws Exception {
        List<InputData> inputDataList = new ArrayList<>();
        List<ReferenceData> referenceDataList = new ArrayList<>();


        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputFile.getInputStream(), StandardCharsets.UTF_8));
            BufferedReader referenceReader = new BufferedReader(new InputStreamReader(referenceFile.getInputStream(), StandardCharsets.UTF_8));
            processInputCsvFile(inputReader, inputDataList);
            processReferenceCsvFile(referenceReader, referenceDataList);

        } catch (Exception e) {
            throw new Exception("Failed to process CSV files: " + e.getMessage());
        }

        try {
            inputDataRepository.saveAll(inputDataList);
            referenceDataRepository.saveAll(referenceDataList);
        } catch (DataAccessException e) {
            throw new Exception("Failed to save data to repositories: " + e.getMessage());
        }
        try{
            List<ReportData> reportDataList = reportGenerationService.generateReport();

            if(!reportDataList.isEmpty()) {
                return reportDataList;
            }
            return null;
        } catch (Exception e){
            throw new Exception("Failed to save data to repositories: " + e.getMessage());
        }
    }

    private void processInputCsvFile(BufferedReader reader, List<InputData> dataList) throws Exception {
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        for (CSVRecord csvRecord : csvParser) {
            try {
                System.out.println(csvRecord);

                InputData inputData = new InputData();

                inputData.setField1(csvRecord.get("field1"));
                inputData.setField2(csvRecord.get("field2"));
                inputData.setField3(csvRecord.get("field3"));
                inputData.setField4(csvRecord.get("field4"));
                inputData.setField5(Double.valueOf(csvRecord.get("field5")));
                inputData.setRefkey1(csvRecord.get("refkey1"));
                inputData.setRefkey2(csvRecord.get("refkey2"));

                dataList.add(inputData);

            } catch (Exception e) {
                throw new Exception("Failed to parse CSV record: " + e.getMessage());
            }
        }
    }

    private void processReferenceCsvFile(BufferedReader reader, List<ReferenceData> dataList) throws Exception {
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        for (CSVRecord csvRecord : csvParser) {
            try {
                ReferenceData referenceData = new ReferenceData();

                referenceData.setRefkey1(csvRecord.get("refkey1"));
                referenceData.setRefkey2(csvRecord.get("refkey2"));
                referenceData.setRefdata1(csvRecord.get("refdata1"));
                referenceData.setRefdata2(csvRecord.get("refdata2"));
                referenceData.setRefdata3(csvRecord.get("refdata3"));
                referenceData.setRefdata4(Double.valueOf(csvRecord.get("refdata4")));

                dataList.add(referenceData);

            } catch (Exception e) {
                throw new Exception("Failed to parse CSV record: " + e.getMessage());
            }
        }
    }

    public List<ReportData> generateExistingDataReport() {
        List<ReportData> reportDataList = reportGenerationService.generateReport();
        return reportDataList;
    }

    public List<ReportData> getDataReport() {
        List<OutputData> outputData = new ArrayList<>();
        List<ReportData> reportDataList = new ArrayList<>();

        for (OutputData data : outputData) {
            ReportData reportData = mapper.map(data, ReportData.class);
            reportDataList.add(reportData);
        }
        return reportDataList;
    }
}
