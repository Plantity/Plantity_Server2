package com.plantity.server.domain.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersRequestDto {


    private String nickname;

    private String socialType; //kakao

    private String userId; //id



    @Getter
    @Setter
    public static class SignUpDto{
        private String socialType;

        private String socialToken;

        private String nickname;
    }

    @Getter
    public static class ProfileDto {
        private String nickname;

        private Integer score;

        public ProfileDto(Users user){
            nickname = user.getNickname();
            score = user.getScore();
        }
    }



    private UsersRequestDto(String userId){
        this.userId = userId;
    }

    public static UsersRequestDto of(String userId) {
        return new UsersRequestDto(userId);
    }


    @Getter
    @Setter
    public static class SigninDto{

        @NotNull
        private String socialType;

        @NotNull
        private String socialToken;
    }
}
