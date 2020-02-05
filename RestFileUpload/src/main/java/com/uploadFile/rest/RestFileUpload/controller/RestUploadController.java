package com.uploadFile.rest.RestFileUpload.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

public class RestUploadController {

    private final Logger logger = LoggerFactory.getLogger(RestController.class);

    @PostMapping("/api/upload")
    public ResponseEntity<?> uploadFile() {

        return null;
    }

}
