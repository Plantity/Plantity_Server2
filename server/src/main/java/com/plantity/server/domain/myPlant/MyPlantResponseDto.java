package com.plantity.server.domain.myPlant;

import com.plantity.server.domain.plantlog.Log;
import com.plantity.server.domain.plantlog.PlantLog;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyPlantResponseDto {

    private String plantName;
    private String filePath;
    private Long userId;
    private PlantLog plantLog;

    private MyPlantResponseDto(MyPlant myPlant) {
        this.plantName = myPlant.getPlantName();
        this.filePath = myPlant.getFilePath();
        this.userId = myPlant.getUserIdx();
    }

    public static MyPlantResponseDto from(MyPlant myPlant) {
        return new MyPlantResponseDto(myPlant);
    }
}
