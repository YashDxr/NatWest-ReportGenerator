package com.yash.natwestassignmentreportgenerator.Controllers;


import com.yash.natwestassignmentreportgenerator.Models.InputData;
import com.yash.natwestassignmentreportgenerator.Models.ReferenceData;
import com.yash.natwestassignmentreportgenerator.Models.ReportData;
import com.yash.natwestassignmentreportgenerator.Services.ReportGenerationService;
import com.yash.natwestassignmentreportgenerator.Services.SchedulingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportGenerationController {

    private static final Logger logger = LoggerFactory.getLogger(ReportGenerationController.class);

    @Autowired
    private ReportGenerationService reportGenerationService;

    @Autowired
    private SchedulingService schedulingService;


    @GetMapping("/inputdata")
    public ResponseEntity<List<InputData>> getInputRecords(){
        logger.info("getInputRecords");
        try{
            List<InputData> records = reportGenerationService.findInputRecords();
            return ResponseEntity.ok(records);
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/referencedata")
    public ResponseEntity<List<ReferenceData>> getReferenceRecords(){
        logger.info("getReferenceRecords");
        try{
            List<ReferenceData> records = reportGenerationService.findReferenceRecords();
            return ResponseEntity.ok(records);
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/generate")
    public ResponseEntity<List<ReportData>> generateReport() {
        logger.info("generateReport");
        try {
            return ResponseEntity.ok(reportGenerationService.generateReport());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/schedule")
    public ResponseEntity<String> scheduleReportGeneration(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime datetime) {
        logger.info("scheduleReportGeneration");
        try {
            System.out.println(datetime);
            schedulingService.scheduleReportGeneration(datetime);
            return ResponseEntity.ok("Report generation scheduled successfully");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(500).body("Failed to schedule report generation: " + e.getMessage());
        }
    }

}

