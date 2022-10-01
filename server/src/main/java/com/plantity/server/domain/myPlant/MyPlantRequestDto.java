package com.plantity.server.domain.myPlant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MyPlantRequestDto {

    private String userIdx;
    private Long myPlantId;

    private MyPlantRequestDto(String userIdx, Long myPlantId) {
        this.userIdx = userIdx;
        this.myPlantId = myPlantId;
    }

    public static MyPlantRequestDto of(String userIdx, Long myPlantId) {
        return new MyPlantRequestDto(userIdx, myPlantId);
    }
}
