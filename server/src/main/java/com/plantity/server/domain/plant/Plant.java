package com.plantity.server.domain.plant;

import com.plantity.server.domain.plantFollowing.PlantFollowing;
import com.plantity.server.domain.users.Users;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Plant {

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

    /*
    public void addFollowingPlant(PlantFollowing plantFollowing) {
        plantFollowing.add();
    }

     */

}
