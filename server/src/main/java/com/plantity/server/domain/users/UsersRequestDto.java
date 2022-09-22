package com.plantity.server.domain.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UsersRequestDto {

    private String userId;
    private String nickName;
    private String rating;
    private String social;
    private int score;

    private UsersRequestDto(String userId){
        this.userId = userId;
    }

    public static UsersRequestDto of(String userId) {
        return new UsersRequestDto(userId);
    }


    @Getter
    @Setter
    public static class SocialSignupDto{

        @NotNull
        @JsonProperty("social_token")
        private String socialToken;

        @NotNull
        @JsonProperty("social_type")
        private String socialType;

        private String nickName;
        private String social; //이메일
    }

    @Getter
    @Setter
    public static class SocialSigninDto{

        @NotNull
        @JsonProperty("social_type")
        private String socialType;

        @NotNull
        @JsonProperty("social_token")
        private String socialToken;
    }
}
