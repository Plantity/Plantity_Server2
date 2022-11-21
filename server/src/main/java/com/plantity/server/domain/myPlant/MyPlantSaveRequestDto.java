package com.plantity.server.domain.myPlant;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MyPlantSaveRequestDto {

    private String plantName; //식물이름
    private String plantNickName; //식물 별명
    private String filePath;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String plantAdaptTime; //입양날짜

    @Builder
    public MyPlantSaveRequestDto(String plantName, String plantNickName, String filePath, String plantAdaptTime) {

        //식물이름, 타입, 이미지, 입양날짜
        this.plantName = plantName; //식물이름
        this.plantNickName = plantNickName; //타입
        this.filePath = filePath; //이미지
        this.plantAdaptTime = plantAdaptTime; //입양날짜
    }

    public MyPlant toEntity() {
        return MyPlant.builder()
                .plantName(plantName)
                .plantNickName(plantNickName)
                .filePath(filePath)
                .plantAdaptTime(plantAdaptTime)
                .build();
    }
}
