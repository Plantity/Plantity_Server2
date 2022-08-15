package com.plantity.server.domain;

import com.plantity.server.domain.plantFollowing.PlantFollowing;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Plant {

    @Id @GeneratedValue
    @Column(name="plant_Idx")
    private Long plantIdx; //식물 번호

    @Column(name="user-id2")
    private Long userId2; // 사용자 아이디


    public void addFollowingPlant(PlantFollowing plantFollowing) {
        plantFollowing.add();
    }

    private String status;
    private LocalDateTime CreatedAt;
}
