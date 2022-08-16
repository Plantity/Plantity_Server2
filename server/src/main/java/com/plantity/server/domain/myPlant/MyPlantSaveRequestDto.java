package com.plantity.server.domain.myPlant;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyPlantSaveRequestDto {

    private String plantName;
    private String plantType;
    private int level;
    private String content;
    private String plantImage;
    private String status;

    @Builder
    public MyPlantSaveRequestDto(String plantName, String plantType, int level, String content, String plantImage, String status) {

        this.plantName = plantName;
        this.plantType = plantType;
        this.level = level;
        this.content = content;
        this.plantImage = plantImage;
        this.status = status;
    }

    public MyPlant toEntity() {
        return MyPlant.builder()
                .plantName(plantName)
                .plantType(plantType)
                .level(level)
                .content(content)
                .plantImage(plantImage)
                .status(status)
                .build();
    }
}
