package com.yash.natwestassignmentreportgenerator.Controllers;


import com.yash.natwestassignmentreportgenerator.Models.OutputData;
import com.yash.natwestassignmentreportgenerator.Services.ReportGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportGenerationController {

    @Autowired
    private ReportGenerationService reportGenerationService;

    @GetMapping("/generate")
    public ResponseEntity<List<OutputData>> generateReport() {
        try {

            return ResponseEntity.ok(reportGenerationService.generateReport());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/schedule")
    public ResponseEntity<String> scheduleReportGeneration(@RequestParam String cronExpression) {
        try {
            reportGenerationService.scheduleReportGeneration(cronExpression);
            return ResponseEntity.ok("Report generation scheduled successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to schedule report generation: " + e.getMessage());
        }
    }
}

