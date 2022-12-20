package com.plantity.server.domain.users;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.plantity.server.domain.BaseTimeEntity;
import com.plantity.server.domain.myPlant.MyPlant;
import com.plantity.server.domain.plantFollowing.PlantFollowing;
import com.plantity.server.domain.plantlog.PlantLog;
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
    private Long userId;

    private String nickName;
    //private String social;
    private String rating;
    private int score;
    //private String status;

    @JsonIgnore
    @OneToMany(mappedBy = "users")
    private List<MyPlant> myPlant = new ArrayList<MyPlant>();

    @JsonIgnore
    @OneToMany(mappedBy = "users")
    private List<PlantLog> plantLogs = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "users")
    private List<PlantFollowing> plantFollowings = new ArrayList<PlantFollowing>();

    public Users(Users users){
        this.userId = users.userId;
        this.nickName = users.nickName;
        //this.social = users.social;
        this.rating = users.rating;
        this.score = users.score;
       // this.status = users.status;
    }

    public Users(UsersRequestDto usersRequestDto){
        this.nickName = usersRequestDto.getNickName();
        //this.social = usersRequestDto.getSocial();
        this.rating = usersRequestDto.getRating();
        this.score = usersRequestDto.getScore();
        //this.status = usersRequestDto.getStatus();
    }

    // 광합성
    public void updateSun(){
        score += 5;
        checkRating();
    }

    // 분갈이
    public void updateRepot(){
        score += 20;
        checkRating();
    }

    // 물 주기
    public void updateWater(){
        score += 3;
        checkRating();
    }

    // 관찰
    public void updateLook(){
        score += 2;
        checkRating();
    }

    public void checkRating(){
        if(score <= 30)
            rating = "비기너";
        else if(score <= 100)
            rating = "가드너";
        else
            rating = "마스터";
    }
}