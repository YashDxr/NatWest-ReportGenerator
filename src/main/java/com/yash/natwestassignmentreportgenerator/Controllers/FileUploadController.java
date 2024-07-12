package com.yash.natwestassignmentreportgenerator.Controllers;

import com.yash.natwestassignmentreportgenerator.Models.ReportData;
import com.yash.natwestassignmentreportgenerator.Services.FileProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    @Autowired
    private FileProcessingService fileProcessingService;

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<List<ReportData>> uploadFile(@RequestParam("inputfile") MultipartFile inputFile,
                                                       @RequestParam("referencefile") MultipartFile referenceFile) {
        logger.info("Uploading files and generating report");
        try {
            return ResponseEntity.ok(fileProcessingService.saveFile(inputFile, referenceFile));
        } catch (Exception e) {
            logger.error("Error while uploading files", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/generatereport")
    public ResponseEntity<List<ReportData>> generateReportFromExistingInputAndReferenceTables(){
        logger.info("Generating report");
        try{
            List<ReportData> reportData = fileProcessingService.generateExistingDataReport();
            return ResponseEntity.ok(reportData);
        }catch(Exception e){
            logger.error("Error while generating report", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/getreport")
    public ResponseEntity<List<ReportData>> getReportFromOutputTable(){
        logger.info("Getting report");
        try{
            List<ReportData> reportData = fileProcessingService.getDataReport();
            return ResponseEntity.ok(reportData);
        }catch(Exception e){
            logger.error("Error while getting report", e);
            return ResponseEntity.status(500).body(null);
        }
    }
}

