package com.plantity.server.domain.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UsersRequestDto {
    private String nickName;
    private String social;
    private String rating;
    private int score;
    private String status;

    public UsersRequestDto(String nickName, String social, String rating, int score, String status){
        this.nickName = nickName;
        this.social = social;
        this.rating = rating;
        this.score = score;
        this.status = status;

    }

}
