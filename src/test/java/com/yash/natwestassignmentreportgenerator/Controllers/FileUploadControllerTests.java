package com.yash.natwestassignmentreportgenerator.Controllers;

import com.yash.natwestassignmentreportgenerator.Models.OutputData;
import com.yash.natwestassignmentreportgenerator.Models.ReportData;
import com.yash.natwestassignmentreportgenerator.Repositories.OutputDataRepository;
import com.yash.natwestassignmentreportgenerator.Services.FileProcessingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FileUploadControllerTests {

    @Autowired
    FileProcessingService fileService;

    @MockBean
    OutputDataRepository outputDataRepository;


    @Test
    public void testGetReportFromOutputTable(){
        when(outputDataRepository.findAll()).thenReturn(Stream.of(new OutputData("Test", "Test", "Test", "Test", 0.5), new OutputData("Test", "Test", "Test", "Test", 0.5)).toList());
        List<ReportData> list = fileService.getDataReport();
        assertEquals(String.class, list.get(0).getOutfield1().getClass());
        assertEquals(String.class, list.get(0).getOutfield2().getClass());
        assertEquals(String.class, list.get(0).getOutfield3().getClass());
        assertEquals(String.class, list.get(0).getOutfield4().getClass());
        assertEquals(Double.class, list.get(0).getOutfield5().getClass());
    }

    @Test
    public void testGenerateReportFromExistingInputAndReferenceTables(){
        when(outputDataRepository.findAll()).thenReturn(Stream.of(new OutputData("Test", "Test", "Test", "Test", 0.5), new OutputData("Test", "Test", "Test", "Test", 0.5)).toList());

        List<ReportData> list = fileService.generateExistingDataReport();
        assertEquals(String.class, list.get(0).getOutfield1().getClass());
        assertEquals(String.class, list.get(0).getOutfield2().getClass());
        assertEquals(String.class, list.get(0).getOutfield3().getClass());
        assertEquals(String.class, list.get(0).getOutfield4().getClass());
        assertEquals(Double.class, list.get(0).getOutfield5().getClass());
    }

}
