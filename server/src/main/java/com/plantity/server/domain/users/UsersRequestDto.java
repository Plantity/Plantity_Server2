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
    private String social;
    private String nickName;
    private String rating;
    private int score;

    private UsersRequestDto(String userId){
        this.userId = userId;
    }

    public static UsersRequestDto of(String userId) {
        return new UsersRequestDto(userId);
    }

    @Getter
    @Setter
    public static class SignupDto {
        @NotNull
        @JsonProperty("social_token")
        private String socialToken;

        @NotNull
        @JsonProperty("social_type")
        private String socialType;

        private String nickName;
        private String social;
    }

    @Getter
    @Setter
    public static class SigninDto{

        @NotNull
        @JsonProperty("social_type")
        private String socialType;

        @NotNull
        @JsonProperty("social_token")
        private String socialToken;
    }
}
