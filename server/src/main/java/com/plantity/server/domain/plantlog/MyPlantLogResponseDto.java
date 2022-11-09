package com.plantity.server.domain.plantlog;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class MyPlantLogResponseDto {

    private Long plantId;
    private boolean water;
    private boolean look;
    private boolean sun;
    private boolean repot;

    private MyPlantLogResponseDto(PlantLog plantLog) {
        this.plantId = plantLog.getPlantId();
        this.water = plantLog.waterInfo(water);
        this.look = plantLog.lookInfo(look);
        this.sun = plantLog.sunInfo(sun);
        this.repot = plantLog.repotInfo(repot);
    }

    public static MyPlantLogResponseDto from(PlantLog plantLog){
        return new MyPlantLogResponseDto(plantLog);
    }

}
