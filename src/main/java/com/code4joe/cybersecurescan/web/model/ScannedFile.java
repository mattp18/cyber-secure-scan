package com.code4joe.cybersecurescan.web.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScannedFile {
    private String fileName;
    private Long size;
    private String contentType;
    private Boolean passScan;
    private String virusSignature;
}
