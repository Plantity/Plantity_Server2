package com.plantity.server.domain.plantlog;


import com.plantity.server.domain.myPlant.MyPlant;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlantLogSaveRequestDto {

    private Long userId;
    private MyPlant myPlant;
    private Log log;

    @Builder
    public PlantLogSaveRequestDto(Long userId, MyPlant myPlant, Log log) {

        this.userId = userId;
        this.myPlant = myPlant;
        this.log = log;
    }

    /*
    public PlantLog toEntity() {
        return PlantLog.builder()
                .plantId(userId)
                .log(log)
                .build();
    }
     */
}
