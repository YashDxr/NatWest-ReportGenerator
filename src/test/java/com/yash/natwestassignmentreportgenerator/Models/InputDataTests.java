package com.yash.natwestassignmentreportgenerator.Models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class InputDataTests {

    @Test
    public void testGettersAndSetters() {
        InputData inputData = new InputData();
        inputData.setRefkey1("Ref1");
        inputData.setRefkey2("Ref2");
        inputData.setField1("Field1");
        inputData.setField2("Field2");
        inputData.setField3("Field3");
        inputData.setField5(0.5);

        assertEquals("Ref1", inputData.getRefkey1());
        assertEquals("Ref2", inputData.getRefkey2());
        assertEquals("Field1", inputData.getField1());
        assertEquals("Field2", inputData.getField2());
        assertEquals("Field3", inputData.getField3());
        assertEquals(0.5, inputData.getField5());
    }

    @Test
    public void testEqualsAndHashCode() {
        InputData inputData1 = new InputData("Test", "Test", "Test", "Test", 0.5, "Test", "Test");
        InputData inputData2 = new InputData("Test", "Test", "Test", "Test", 0.5, "Test", "Test");
        InputData inputData3 = new InputData("Test1", "Test1", "Test1", "Test1", 0.6, "Test1", "Test1");

        assertTrue(inputData1.equals(inputData2));
        assertEquals(inputData1.hashCode(), inputData2.hashCode());
        assertTrue(!inputData1.equals(inputData3));
        assertTrue(inputData1.hashCode() != inputData3.hashCode());
    }

}
