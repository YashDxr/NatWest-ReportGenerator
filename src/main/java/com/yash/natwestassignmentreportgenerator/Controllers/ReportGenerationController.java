package com.yash.natwestassignmentreportgenerator.Controllers;


import com.yash.natwestassignmentreportgenerator.Models.InputData;
import com.yash.natwestassignmentreportgenerator.Models.ReportData;
import com.yash.natwestassignmentreportgenerator.Repositories.InputDataRepository;
import com.yash.natwestassignmentreportgenerator.Services.ReportGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportGenerationController {

    @Autowired
    private ReportGenerationService reportGenerationService;

    @Autowired
    private InputDataRepository repo;

    @GetMapping("/rep")
    @ResponseBody
    public List<InputData> getReps(){
        return repo.findAll();
    }

    @GetMapping("/generate")
    public ResponseEntity<List<ReportData>> generateReport() {
        try {
            System.out.println("Came to Reportcontroller");
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

