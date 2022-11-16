package com.plantity.server.domain.plantlog;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DateMyPlantLogResponseDto {

    private Long plantId;
    private LocalDate date;
    private boolean water;
    private boolean look;
    private boolean sun;
    private boolean repot;

    private DateMyPlantLogResponseDto(PlantLog plantLog) {
        this.plantId = plantLog.getPlantId();
        this.date = plantLog.getLogDate();
        this.water = plantLog.waterInfo(water);
        this.look = plantLog.lookInfo(look);
        this.sun = plantLog.sunInfo(sun);
        this.repot = plantLog.repotInfo(repot);
    }

    public static DateMyPlantLogResponseDto from(PlantLog plantLog){
        return new DateMyPlantLogResponseDto(plantLog);
    }
}
