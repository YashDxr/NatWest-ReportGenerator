package com.yash.natwestassignmentreportgenerator.Models;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ReferenceDataTests {
    @Test
    public void testNoArgsConstructor() {
        ReferenceData referenceData = new ReferenceData();
        assertNotNull(referenceData);
    }

    @Test
    public void testAllArgsConstructor() {
        ReferenceData referenceData = new ReferenceData(1L, "ref1", "ref2", "data1", "data2", "data3", 0.5);
        assertEquals(Long.valueOf(1), referenceData.getId());
        assertEquals("ref1", referenceData.getRefkey1());
        assertEquals("ref2", referenceData.getRefkey2());
        assertEquals("data1", referenceData.getRefdata1());
        assertEquals("data2", referenceData.getRefdata2());
        assertEquals("data3", referenceData.getRefdata3());
        assertEquals(Double.valueOf(0.5), referenceData.getRefdata4());
    }

    @Test
    public void testPartialConstructor() {
        ReferenceData referenceData = new ReferenceData("ref1", "ref2", "data1", "data2", 0.5);
        assertEquals("ref1", referenceData.getRefkey1());
        assertEquals("ref2", referenceData.getRefkey2());
        assertEquals("data1", referenceData.getRefdata1());
        assertEquals("data2", referenceData.getRefdata2());
        assertEquals("data2", referenceData.getRefdata3());
        assertEquals(Double.valueOf(0.5), referenceData.getRefdata4());
    }

    @Test
    public void testGettersAndSetters() {
        ReferenceData referenceData = new ReferenceData();
        referenceData.setId(1L);
        referenceData.setRefkey1("ref1");
        referenceData.setRefkey2("ref2");
        referenceData.setRefdata1("data1");
        referenceData.setRefdata2("data2");
        referenceData.setRefdata3("data3");
        referenceData.setRefdata4(0.5);

        assertEquals(Long.valueOf(1), referenceData.getId());
        assertEquals("ref1", referenceData.getRefkey1());
        assertEquals("ref2", referenceData.getRefkey2());
        assertEquals("data1", referenceData.getRefdata1());
        assertEquals("data2", referenceData.getRefdata2());
        assertEquals("data3", referenceData.getRefdata3());
        assertEquals(Double.valueOf(0.5), referenceData.getRefdata4());
    }
}
