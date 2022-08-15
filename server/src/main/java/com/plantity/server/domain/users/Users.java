package com.plantity.server.domain.users;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String nickName;
    private String social;
    private String rating;
    private int score;
    private String status;

    private List<PlantFollowing> plantFollowings;

    public Users(String nickName, String social, String rating, int score, String status){
        this.nickName = nickName;
        this.social = social;
        this.rating = rating;
        this.score = score;
        this.status = status;

    }

    public Users(UsersRequestDto userRequestDto){
        this.nickName = userRequestDto.getNickName();
        this.social = userRequestDto.getSocial();
        this.rating = userRequestDto.getRating();
        this.score = userRequestDto.getScore();
        this.status = userRequestDto.getStatus();
    }

    public void updateUser(UsersRequestDto userRequestDto){
        this.nickName = userRequestDto.getNickName();
        this.social = userRequestDto.getSocial();
        this.rating = userRequestDto.getRating();
        this.score = userRequestDto.getScore();
        this.status = userRequestDto.getStatus();
    }
}