package com.plantity.server.domain.users;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersResponseDto {

    private String nickName;
    private String rating;
    private int score;
    private String social;

    private UsersResponseDto(Users users) {
        this.social = users.getSocial();
        this.nickName = users.getNickname();
        this.rating = users.getRating();
        this.score = users.getScore();
    }

    public static UsersResponseDto from(Users users) {
        return new UsersResponseDto(users);
    }
}
