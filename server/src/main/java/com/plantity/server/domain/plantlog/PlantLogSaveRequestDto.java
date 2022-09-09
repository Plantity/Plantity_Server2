package com.plantity.server.domain.plantlog;


import com.plantity.server.domain.myPlant.MyPlant;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlantLogSaveRequestDto {

    private MyPlant myPlant;
    private Log log;

    @Builder
    public PlantLogSaveRequestDto(MyPlant myPlant, Log log) {

        this.myPlant = myPlant;
        this.log = log;
    }

    public PlantLog toEntity() {
        return PlantLog.builder()
                .myPlant(myPlant)
                .log(log)
                .build();
    }
}
