package com.plantity.server.domain.plantlog;

import com.plantity.server.domain.myPlant.MyPlant;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlantLogUpdateRequestDto {

    private Long plantId;
    private MyPlant myPlant;
    private Log log;

    @Builder
    public PlantLogUpdateRequestDto(MyPlant myPlant, Log log) {
        this.log.updateSun(true);
    }
}
