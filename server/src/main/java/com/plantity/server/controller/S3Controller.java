package com.plantity.server.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.plantity.server.domain.testimg.TestImgData;
import com.plantity.server.repository.TestImgRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class S3Controller {

    private final AmazonS3 amazonS3;

    private final TestImgRepository testImgRepository;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;


    @PostMapping("/image")
    public String createUserImage(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        try {
            String oriFileName = multipartFile.getOriginalFilename();
            String fileName = oriFileName;
            String filePath = "postImg/" + fileName;
            Long fileSize = multipartFile.getSize();
            String contentType = multipartFile.getContentType();

            // S3 이미지 삽입
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(fileSize);
            objectMetadata.setContentType(contentType);
            amazonS3.putObject(bucket, filePath, multipartFile.getInputStream(), objectMetadata);

            // 데이터 삽입
            TestImgData testImgData = new TestImgData(oriFileName, fileName, amazonS3.getUrl(bucket, filePath).toString(), fileSize);
            testImgRepository.save(testImgData);

        } catch (Exception exception) {
            logger.error("Error!", exception);
            return "fail";
        }
        return "success";
    }

}
