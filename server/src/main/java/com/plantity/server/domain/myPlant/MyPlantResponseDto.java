package com.plantity.server.domain.myPlant;

import com.plantity.server.domain.plantlog.Log;
import com.plantity.server.domain.plantlog.PlantLog;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyPlantResponseDto {

    private Long myPlantId;
    private String plantName;
    private String plantNickName;
    private String plantAdaptTime;
    private String filePath;
    //private PlantLog plantLog;

    private MyPlantResponseDto(MyPlant myPlant) {
        this.plantName = myPlant.getPlantName();
        this.filePath = myPlant.getFilePath();
        this.myPlantId = myPlant.getMyPlantId();
        this.plantNickName = myPlant.getPlantNickName();
        this.plantAdaptTime = myPlant.getPlantAdaptTime();
    }

    public static MyPlantResponseDto from(MyPlant myPlant) {
        return new MyPlantResponseDto(myPlant);
    }
}
