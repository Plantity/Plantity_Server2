package com.plantity.server.domain.myPlant;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyPlantDetailRes {

    private Long myPlantId;
    private String plantNickName;
    private String plantName;
    private String adaptDate;
    private String waterCycle; // 관수 설명
    private String filePath;

    private MyPlantDetailRes(MyPlant myPlant){
        this.myPlantId = myPlant.getMyPlantId();
        this.plantNickName = myPlant.getPlantNickName();
        this.plantName = myPlant.getPlantName();
        this.adaptDate = myPlant.getPlantAdaptTime();
        this.waterCycle = myPlant.getWatercycleSprngCodeNm();
        this.filePath = myPlant.getFilePath();
    }

    public static MyPlantDetailRes from(MyPlant myPlant){
        return new MyPlantDetailRes(myPlant);
    }
}
