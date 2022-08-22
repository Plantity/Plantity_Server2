package com.plantity.server.domain.users;


import com.plantity.server.domain.BaseTimeEntity;
import com.plantity.server.domain.myPlant.MyPlant;
import com.plantity.server.domain.plantFollowing.PlantFollowing;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
public class Users extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId; //회원 id

    private String nickName; // 회원 닉네임
    private String social; // 소셜로그인
    private String rating; // 회원 등급
    private int score; // 점수
    private String status; // 회원 상태

    @OneToMany(mappedBy = "users")
    private List<MyPlant> myPlant = new ArrayList<MyPlant>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<PlantFollowing> plantFollowings = new ArrayList<PlantFollowing>();


}