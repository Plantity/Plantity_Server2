package com.plantity.server.domain.plantlog;

import com.plantity.server.domain.myPlant.MyPlant;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlantLogUpdateRequestDto {

    private Long plantId;
    private PlantLog plantLog;
    private Log log;

    @Builder
    public PlantLogUpdateRequestDto(PlantLog plantLog, Log log) {
        this.plantLog = plantLog;
        this.log = log;
    }
}
