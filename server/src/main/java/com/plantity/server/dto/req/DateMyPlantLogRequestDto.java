package com.plantity.server.dto.req;

import com.plantity.server.domain.myPlant.MyPlant;
import com.plantity.server.domain.myPlant.MyPlantRequestDto;
import com.plantity.server.domain.plantlog.PlantLog;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class DateMyPlantLogRequestDto {

    private Long userIdx;
    private Long plantId;
    private LocalDate logDate;


    @Builder
    public DateMyPlantLogRequestDto(Long userIdx, Long plantId, LocalDate logDate) {
        this.userIdx = userIdx;
        this.plantId = plantId;
        this.logDate = logDate;
    }

    public PlantLog toEntity() {
        return PlantLog.builder()
                .userIdx(userIdx)
                .plantId(plantId)
                .logDate(logDate)
                .build();
    }




}
