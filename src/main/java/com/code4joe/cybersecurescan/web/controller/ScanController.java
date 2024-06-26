package com.code4joe.cybersecurescan.web.controller;

import com.code4joe.cybersecurescan.backend.service.MalwareScanService;
import com.code4joe.cybersecurescan.web.model.ScannedFile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api/v1/scan")
public class ScanController {

    private final MalwareScanService malwareScanService;

    public ScanController(MalwareScanService malwareScanService) {
        this.malwareScanService = malwareScanService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> scanFileForMalware(@RequestParam("file") MultipartFile file) throws IOException {
        ScannedFile fileToBeScanned = new ScannedFile();
        fileToBeScanned.setFileName(file.getOriginalFilename());
        fileToBeScanned.setContentType(file.getContentType());
        fileToBeScanned.setSize(file.getSize());

        return ResponseEntity.ok(malwareScanService.scanFile(file.getInputStream(), fileToBeScanned));
    }
}
