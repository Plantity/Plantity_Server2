package com.plantity.server.domain.plantlog;

import com.plantity.server.domain.myPlant.MyPlant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MyPlantLogRequestDto {

    private String userId;
    private Long myPlantId;
    private Long myPlantLogId;

    private MyPlantLogRequestDto(String userId, Long myPlantId, Long myPlantLogId) {
        this.userId = userId;
        this.myPlantId = myPlantId;
        this.myPlantLogId = myPlantLogId;
    }

    public static MyPlantLogRequestDto of(String userId, Long myPlantId, Long myPlantLogId) {
        return new MyPlantLogRequestDto(userId, myPlantId, myPlantLogId);
    }
}
