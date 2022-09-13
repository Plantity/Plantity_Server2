package com.plantity.server.domain.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UsersRequestDto {

    private Long userId;
    private String nickName;
    private String rating;
    private int score;

    private UsersRequestDto(Long userId){
        this.userId = userId;
        //this.nickName = nickName;
        //this.rating = rating;
        //this.score = score;
    }

    public static UsersRequestDto of(Long userId) {
        return new UsersRequestDto(userId);
    }

}
