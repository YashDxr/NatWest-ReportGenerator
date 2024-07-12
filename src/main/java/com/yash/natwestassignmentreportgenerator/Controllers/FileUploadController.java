package com.yash.natwestassignmentreportgenerator.Controllers;

import com.yash.natwestassignmentreportgenerator.Models.OutputData;
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
    public ResponseEntity<List<ReportData>> uploadFile(@RequestParam("inputfile") MultipartFile inputFile,
                                                       @RequestParam("referencefile") MultipartFile referenceFile) {
        try {
            System.out.println("Going to Fileservice");
            return ResponseEntity.ok(fileProcessingService.saveFile(inputFile, referenceFile));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}

