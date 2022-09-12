package com.plantity.server.domain.testimg;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Entity
@Getter
public class TestImgData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long testId;

    private String origFileName;  // 파일 원본명
    private String fileName; // 파일 이름 변환명
    private String filePath;  // 파일 저장 경로
    private Long fileSize;

    public TestImgData(String origFileName, String fileName, String filePath, Long fileSize){
        this.origFileName = origFileName;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
}
