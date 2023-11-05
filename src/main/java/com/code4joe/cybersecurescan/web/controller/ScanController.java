package com.code4joe.cybersecurescan.web.controller;

import com.code4joe.cybersecurescan.backend.service.MalwareScanService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<String> scanFileForMalware(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(malwareScanService.scanFile(file.getInputStream()));
    }
}
