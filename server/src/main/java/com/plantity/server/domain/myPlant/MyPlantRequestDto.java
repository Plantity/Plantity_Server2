package com.plantity.server.domain.myPlant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MyPlantRequestDto {

    private Long userIdx;
    private Long myPlantId;

    private MyPlantRequestDto(Long userIdx, Long myPlantId) {
        this.userIdx = userIdx;
        this.myPlantId = myPlantId;
    }

    public static MyPlantRequestDto of(Long userIdx, Long myPlantId) {
        return new MyPlantRequestDto(userIdx, myPlantId);
    }
}
