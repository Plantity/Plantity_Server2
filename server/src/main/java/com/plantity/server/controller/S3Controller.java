package com.plantity.server.controller;

import com.plantity.server.config.BaseResponse2;
import com.plantity.server.utils.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class S3Controller {
    private final S3Uploader s3Uploader;
    final Logger logger = LoggerFactory.getLogger(this.getClass());


    @PostMapping("/image")
    public String updateUserImage(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        try {
            s3Uploader.upload(multipartFile);
        } catch (Exception exception) {
            logger.error("Error!", exception);
            return "fail";
        }
        return "success";
    }

}
