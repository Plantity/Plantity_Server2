package com.plantity.server.domain.Plant;

import com.plantity.server.domain.myPlant.MyPlant;
import lombok.Builder;

public class PlantRequestDto {

    private String plntbneNm; // 학명(식물명)
    private String plntzrNm; // 영문명
    private String adviseInfo; // 식물설명
    private String orgplceInfo; // 원산지
    private String lighttdemanddoCodeNm; // 광도
    private String ignSeasonCodeNm; // 개화 시기
    private String flclrCodeNm; // 꽃 색

    @Builder
    public PlantRequestDto(String plntbneNm, String plntzrNm, String adviseInfo, String orgplceInfo, String lighttdemanddoCodeNm, String ignSeasonCodeNm, String flclrCodeNm) {
        this.plntbneNm = plntbneNm;
        this.plntzrNm = plntzrNm;
        this.adviseInfo = adviseInfo;
        this.orgplceInfo = orgplceInfo;
        this.lighttdemanddoCodeNm = lighttdemanddoCodeNm;
        this.ignSeasonCodeNm = ignSeasonCodeNm;
        this.flclrCodeNm = flclrCodeNm;
    }

}
