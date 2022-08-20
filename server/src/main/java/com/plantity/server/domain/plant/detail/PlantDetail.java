package com.plantity.server.domain.plant.detail;

import com.plantity.server.domain.plantFollowing.PlantFollowing;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class PlantDetail {

    @Id @GeneratedValue
    private Long plantIdx; //식물 번호

    // 물주기, 난이도 없음
    private String plntbneNm; // 학명(식물명)
    private String plntzrNm; // 영문명
    private String adviseInfo; // 식물설명
    private String orgplceInfo; // 원산지
    private String lighttdemanddoCodeNm; // 광도
    private String ignSeasonCodeNm; // 개화 시기
    private String flclrCodeNm; // 꽃 색

    @OneToMany(mappedBy = "plant")
    private List<PlantFollowing> plantFollowings = new ArrayList<PlantFollowing>();

    public PlantDetail(String plntbneNm, String plntzrNm, String adviseInfo, String orgplceInfo, String lighttdemanddoCodeNm, String ignSeasonCodeNm, String flclrCodeNm) {
        this.plntbneNm = plntbneNm;
        this.plntzrNm = plntzrNm;
        this.adviseInfo = adviseInfo;
        this.orgplceInfo = orgplceInfo;
        this.lighttdemanddoCodeNm = lighttdemanddoCodeNm;
        this.ignSeasonCodeNm = ignSeasonCodeNm;
        this.flclrCodeNm = flclrCodeNm;
    }

    /*
    public void addFollowingPlant(PlantFollowing plantFollowing) {
        plantFollowing.add();
    }

     */

}
