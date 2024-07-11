package com.yash.natwestassignmentreportgenerator.Controllers;

import com.yash.natwestassignmentreportgenerator.Services.FileProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    @Autowired
    private FileProcessingService fileProcessingService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("inputfile") MultipartFile inputFile,
                                             @RequestParam("referencefile") MultipartFile referenceFile) {
        try {
            fileProcessingService.saveFile(inputFile, referenceFile);
            return ResponseEntity.ok("File uploaded and processed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to process file: " + e.getMessage());
        }
    }
}

