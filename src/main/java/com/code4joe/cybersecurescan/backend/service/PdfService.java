package com.code4joe.cybersecurescan.backend.service;

import com.code4joe.cybersecurescan.web.model.ScannedFile;

import java.util.Collection;
import java.util.Map;

public interface PdfService {
    void generatePdf(ScannedFile fileToBeScanned, Map<String, Collection<String>> viruses);
}
