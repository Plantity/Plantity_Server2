package com.plantity.server.domain.plantlog;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyPlantLogResponseDto {

    private boolean water;
    private boolean look;
    private boolean sun;
    private boolean repot;

    private MyPlantLogResponseDto(PlantLog plantLog) {
        this.water = plantLog.waterInfo(water);
        this.look = plantLog.waterInfo(look);
        this.sun = plantLog.waterInfo(sun);
        this.repot = plantLog.waterInfo(repot);
    }

    public static MyPlantLogResponseDto from(PlantLog plantLog){
        return new MyPlantLogResponseDto(plantLog);
    }

}
