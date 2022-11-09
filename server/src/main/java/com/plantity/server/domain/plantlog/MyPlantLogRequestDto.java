package com.plantity.server.domain.plantlog;

import com.plantity.server.domain.myPlant.MyPlant;
import lombok.*;

@Getter
@RequiredArgsConstructor
public class MyPlantLogRequestDto {

    private Long userId;
    private Long myPlantId;
    private Long myPlantLogId;


    private MyPlantLogRequestDto(Long userId, Long myPlantId, Long myPlantLogId) {
        this.userId = userId;
        this.myPlantId = myPlantId;
        this.myPlantLogId = myPlantLogId;
    }

    public static MyPlantLogRequestDto of(Long userId, Long myPlantId, Long myPlantLogId) {
        return new MyPlantLogRequestDto(userId, myPlantId, myPlantLogId);
    }
}
