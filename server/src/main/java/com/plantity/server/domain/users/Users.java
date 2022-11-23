package com.plantity.server.domain.users;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.plantity.server.domain.BaseTimeEntity;
import com.plantity.server.domain.myPlant.MyPlant;
import com.plantity.server.domain.plantFollowing.PlantFollowing;
import com.plantity.server.domain.plantlog.PlantLog;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Entity
@Table
@Getter
public class Users extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    private String nickname;

    private String userId;

    private String social; // email

    private String socialType;

    private String rating;

    private int score;

    @OneToMany(mappedBy = "users")
    private List<MyPlant> myPlant = new ArrayList<MyPlant>();

    @OneToMany(mappedBy = "users")
    private List<PlantLog> plantLogs = new ArrayList<>();

    @OneToMany(mappedBy = "users")
    private List<PlantFollowing> plantFollowings = new ArrayList<PlantFollowing>();


    public Users(Users users){
        this.socialType = users.socialType;
        this.userId = users.userId;
        this.nickname = users.nickname;
        this.social = users.social;
        this.rating = "rating1";
        this.score = 0;
       // this.status = users.status;
    }

    public Users(UsersRequestDto usersRequestDto) {
        this.socialType = usersRequestDto.getSocialType();
        this.userId = usersRequestDto.getUserId(); //기존의 userId
        this.nickname = usersRequestDto.getNickname();
    }

    public Users(String nickName, String userId, String socialType, String social, String rating, int score){
        this.socialType = socialType;
        this.userId = userId;
        this.nickname = nickName;
        this.social = social;
        this.rating = rating;
        this.score = score;
    }

    public Users(Optional<Users> byUserId) {
        super();
    }

    public void setScore(Integer score){
        this.score = score;
    }

//    public Users(UsersRequestDto usersRequestDto){
//        this.nickName = usersRequestDto.getNickName();
//        this.social = usersRequestDto.getSocial();
//        this.rating = usersRequestDto.getRating();
//        this.score = usersRequestDto.getScore();
//        //this.status = usersRequestDto.getStatus();
//    }

}