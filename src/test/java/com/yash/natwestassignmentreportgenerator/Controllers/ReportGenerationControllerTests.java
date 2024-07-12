package com.yash.natwestassignmentreportgenerator.Controllers;


import com.yash.natwestassignmentreportgenerator.Models.InputData;
import com.yash.natwestassignmentreportgenerator.Models.OutputData;
import com.yash.natwestassignmentreportgenerator.Models.ReferenceData;
import com.yash.natwestassignmentreportgenerator.Repositories.InputDataRepository;
import com.yash.natwestassignmentreportgenerator.Repositories.OutputDataRepository;
import com.yash.natwestassignmentreportgenerator.Repositories.ReferenceDataRepository;
import com.yash.natwestassignmentreportgenerator.Services.ReportGenerationService;
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
public class ReportGenerationControllerTests {
    @Autowired
    ReportGenerationService reportGenerationService;

    @MockBean
    InputDataRepository inputDataRepository;

    @MockBean
    private ReferenceDataRepository referenceDataRepository;

    @MockBean
    private OutputDataRepository outputDataRepository;

    @Test
    public void testGetInputRecords(){
        when(inputDataRepository.findAll()).thenReturn(Stream.of(new InputData("Test", "Test", "Test", "Test", 0.5, "Test", "Test"), new InputData("Test", "Test", "Test", "Test", 0.5, "Test", "Test")).toList());
        List<InputData> records = reportGenerationService.findInputRecords();
        assertEquals(String.class, records.get(0).getField1().getClass());
        assertEquals(String.class, records.get(0).getField2().getClass());
        assertEquals(String.class, records.get(0).getField3().getClass());
        assertEquals(String.class, records.get(0).getField4().getClass());
        assertEquals(Double.class, records.get(0).getField5().getClass());
    }


    @Test
    public void testGetReferenceRecords(){
        when(referenceDataRepository.findAll()).thenReturn(Stream.of(new ReferenceData("Test", "Test", "Test", "Test", 0.5), new ReferenceData("Test", "Test", "Test", "Test", 0.5)).toList());

        List<ReferenceData> records = reportGenerationService.findReferenceRecords();
        assertEquals(String.class, records.get(0).getRefkey1().getClass());
        assertEquals(String.class, records.get(0).getRefkey2().getClass());
        assertEquals(String.class, records.get(0).getRefdata1().getClass());
        assertEquals(String.class, records.get(0).getRefdata3().getClass());
        assertEquals(Double.class, records.get(0).getRefdata4().getClass());
    }

    @Test
    public void testGenerateReport() {

        when(outputDataRepository.findAll()).thenReturn(Stream.of(new OutputData("Test", "Test", "Test", "Test", 0.5), new OutputData("Test", "Test", "Test", "Test", 0.5)).toList());
        List<OutputData> list = outputDataRepository.findAll();
        assertEquals(String.class, list.get(0).getOutfield1().getClass());
        assertEquals(String.class, list.get(0).getOutfield2().getClass());
        assertEquals(String.class, list.get(0).getOutfield3().getClass());
        assertEquals(String.class, list.get(0).getOutfield4().getClass());
        assertEquals(Double.class, list.get(0).getOutfield5().getClass());
    }

}
