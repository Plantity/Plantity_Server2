package com.plantity.server.domain.users;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.plantity.server.domain.BaseTimeEntity;
import com.plantity.server.domain.myPlant.MyPlant;
import com.plantity.server.domain.plantFollowing.PlantFollowing;
import com.plantity.server.domain.plantlog.PlantLog;
import lombok.Builder;
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
    private String userId;

    private String nickName;
    private String social; // email 정보 저장
    private String rating;
    private int score;
    //private String status;

    @OneToMany(mappedBy = "users")
    private List<MyPlant> myPlant = new ArrayList<MyPlant>();

    @OneToMany(mappedBy = "users")
    private List<PlantLog> plantLogs = new ArrayList<>();

    @OneToMany(mappedBy = "users")
    private List<PlantFollowing> plantFollowings = new ArrayList<PlantFollowing>();

    public Users(Users users){
        this.userId = users.userId;
        this.nickName = users.nickName;
        this.social = users.social;
        this.rating = users.rating;
        this.score = users.score;
       // this.status = users.status;
    }
    @Builder
    public Users(String nickName, String social, String rating, int score){
        this.nickName = nickName;
        this.social = social;
        this.rating = rating;
        this.score = score;
    }

    public Users(UsersRequestDto usersRequestDto){
        this.nickName = usersRequestDto.getNickName();
        this.social = usersRequestDto.getSocial();
        this.rating = usersRequestDto.getRating();
        this.score = usersRequestDto.getScore();
        //this.status = usersRequestDto.getStatus();
    }

}