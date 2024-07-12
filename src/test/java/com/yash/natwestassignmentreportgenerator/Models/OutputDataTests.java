package com.yash.natwestassignmentreportgenerator.Models;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OutputDataTests {
    @Test
    public void testNoArgsConstructor() {
        OutputData outputData = new OutputData();
        assertNotNull(outputData);
    }

    @Test
    public void testAllArgsConstructor() {
        OutputData outputData = new OutputData(1L, "out1", "out2", "out3", "out4", 0.5);
        assertEquals(Long.valueOf(1), outputData.getId());
        assertEquals("out1", outputData.getOutfield1());
        assertEquals("out2", outputData.getOutfield2());
        assertEquals("out3", outputData.getOutfield3());
        assertEquals("out4", outputData.getOutfield4());
        assertEquals(Double.valueOf(0.5), outputData.getOutfield5());
    }

    @Test
    public void testPartialConstructor() {
        OutputData outputData = new OutputData("out1", "out2", "out3", "out4", 0.5);
        assertEquals("out1", outputData.getOutfield1());
        assertEquals("out2", outputData.getOutfield2());
        assertEquals("out3", outputData.getOutfield3());
        assertEquals("out4", outputData.getOutfield4());
        assertEquals(Double.valueOf(0.5), outputData.getOutfield5());
    }

    @Test
    public void testGettersAndSetters() {
        OutputData outputData = new OutputData();
        outputData.setId(1L);
        outputData.setOutfield1("out1");
        outputData.setOutfield2("out2");
        outputData.setOutfield3("out3");
        outputData.setOutfield4("out4");
        outputData.setOutfield5(0.5);

        assertEquals(Long.valueOf(1), outputData.getId());
        assertEquals("out1", outputData.getOutfield1());
        assertEquals("out2", outputData.getOutfield2());
        assertEquals("out3", outputData.getOutfield3());
        assertEquals("out4", outputData.getOutfield4());
        assertEquals(Double.valueOf(0.5), outputData.getOutfield5());
    }
}
