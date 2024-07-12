package com.yash.natwestassignmentreportgenerator.Controllers;


import com.yash.natwestassignmentreportgenerator.Models.InputData;
import com.yash.natwestassignmentreportgenerator.Models.ReferenceData;
import com.yash.natwestassignmentreportgenerator.Models.ReportData;
import com.yash.natwestassignmentreportgenerator.Services.ReportGenerationService;
import com.yash.natwestassignmentreportgenerator.Services.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/reports")
public class ReportGenerationController {

    @Autowired
    private ReportGenerationService reportGenerationService;

    @Autowired
    private SchedulingService schedulingService;


    @GetMapping("/inputdata")
    public ResponseEntity<List<InputData>> getInputRecords(){
        try{
            List<InputData> records = reportGenerationService.findInputRecords();
            return ResponseEntity.ok(records);
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/referencedata")
    public ResponseEntity<List<ReferenceData>> getReferenceRecords(){
        try{
            List<ReferenceData> records = reportGenerationService.findReferenceRecords();
            return ResponseEntity.ok(records);
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/generate")
    public ResponseEntity<List<ReportData>> generateReport() {
        try {
            return ResponseEntity.ok(reportGenerationService.generateReport());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/schedule")
    public ResponseEntity<String> scheduleReportGeneration(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime datetime) {
        System.out.println("Entered...");
        try {
            System.out.println(datetime);
            schedulingService.scheduleReportGeneration(datetime);
            return ResponseEntity.ok("Report generation scheduled successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to schedule report generation: " + e.getMessage());
        }
    }

    @GetMapping("/scheduled-tasks")
    public ResponseEntity<String> getScheduledTasks() throws ExecutionException, InterruptedException {
        // Logic to retrieve scheduled tasks or their details
//        String task = reportGenerationService.getTasks();
        return ResponseEntity.ok("");//task);
    }

}

