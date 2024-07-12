package com.yash.natwestassignmentreportgenerator.Controllers;

import com.yash.natwestassignmentreportgenerator.Models.ReportData;
import com.yash.natwestassignmentreportgenerator.Services.FileProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    @Autowired
    private FileProcessingService fileProcessingService;

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<List<ReportData>> uploadFile(@RequestParam("inputfile") MultipartFile inputFile,
                                                       @RequestParam("referencefile") MultipartFile referenceFile) {
        try {
            return ResponseEntity.ok(fileProcessingService.saveFile(inputFile, referenceFile));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/generatereport")
    public ResponseEntity<List<ReportData>> generateReportFromExistingData(){
        try{
            List<ReportData> reportData = fileProcessingService.generateExistingDataReport();
            return ResponseEntity.ok(reportData);
        }catch(Exception e){
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/getreport")
    public ResponseEntity<List<ReportData>> getExistingDataReport(){
        try{
            List<ReportData> reportData = fileProcessingService.getDataReport();
            return ResponseEntity.ok(reportData);
        }catch(Exception e){
            return ResponseEntity.status(500).body(null);
        }
    }
}

