package com.plantity.server.domain.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class UsersRequestDto {


    private String nickname;
    private String social;
    private String rating;
    private int score;
    private String status;




    public UsersRequestDto(String nickname, String social, String rating, int score, String status){
        this.nickname = nickname;
        this.social = social;
        this.rating = rating;
        this.score = score;
        this.status = status;

    }


    @Getter
    @Setter
    public static class ProfileDto {
        @NotNull
        private String nickname;

        public ProfileDto(Users users){
            nickname = users.getNickname();
        }
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

        private String nickname;
        private String email;
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
