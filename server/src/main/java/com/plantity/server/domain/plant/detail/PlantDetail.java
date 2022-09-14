package com.plantity.server.domain.plant.detail;

import com.plantity.server.domain.plantFollowing.PlantFollowing;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
public class PlantDetail {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int plantIdx; //식물 번호

    private String cntntsNo;
    private String cntntsSj;
    private String plntbneNm; // 학명(식물명)
    private String plntzrNm; // 영문명
    private String adviseInfo; // 식물설명
    private String orgplceInfo; // 원산지
    private String lighttdemanddoCodeNm; // 광도
    private String ignSeasonCodeNm; // 개화 시기
    private String flclrCodeNm; // 꽃 색
    private String watercycleSprngCodeNm; // 물주기
    private String managelevelCode; // 관리수준 코드 - 난이도

    /*
    @OneToMany(mappedBy = "plant")
    private List<PlantFollowing> plantFollowings = new ArrayList<PlantFollowing>();
    */

    public PlantDetail(String cntntsNo, String cntntsSj, String plntbneNm, String plntzrNm,
                       String adviseInfo, String orgplceInfo, String lighttdemanddoCodeNm,
                       String ignSeasonCodeNm, String flclrCodeNm,
                       String watercycleSprngCodeNm, String managelevelCode) {
        this.cntntsNo = cntntsNo;
        this.cntntsSj = cntntsSj;
        this.plntbneNm = plntbneNm;
        this.plntzrNm = plntzrNm;
        this.adviseInfo = adviseInfo;
        this.orgplceInfo = orgplceInfo;
        this.lighttdemanddoCodeNm = lighttdemanddoCodeNm;
        this.ignSeasonCodeNm = ignSeasonCodeNm;
        this.flclrCodeNm = flclrCodeNm;
        this.watercycleSprngCodeNm = watercycleSprngCodeNm;
        this.managelevelCode = managelevelCode;
    }

    public PlantDetail(PlantDetail plantDetail) {
        this.cntntsNo = plantDetail.cntntsNo;
        this.cntntsSj = plantDetail.cntntsSj;
        this.plntbneNm = plantDetail.plntbneNm;
        this.plntzrNm = plantDetail.plntzrNm;
        this.adviseInfo = plantDetail.adviseInfo;
        this.orgplceInfo = plantDetail.orgplceInfo;
        this.lighttdemanddoCodeNm = plantDetail.lighttdemanddoCodeNm;
        this.ignSeasonCodeNm = plantDetail.ignSeasonCodeNm;
        this.flclrCodeNm = plantDetail.flclrCodeNm;
        this.watercycleSprngCodeNm = plantDetail.watercycleSprngCodeNm;
        this.managelevelCode = plantDetail.managelevelCode;
    }
    /*
    public PlantDetail(PlantDetail byCntntsNo) {
    }

     */

    /*
    public void addFollowingPlant(PlantFollowing plantFollowing) {
        plantFollowing.add();
    }

     */

}
