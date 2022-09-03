package com.plantity.server.domain.users;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.plantity.server.domain.BaseTimeEntity;
import com.plantity.server.domain.myPlant.MyPlant;
import com.plantity.server.domain.plantFollowing.PlantFollowing;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "user")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users extends BaseTimeEntity {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId; //회원 id

    private String nickname; // 회원 닉네임

    private String social; // 소셜로그인

    private String rating; // 회원 등급
    private int score; // 점수
    private String status; // 회원 상태


    @OneToMany(mappedBy = "users")
    private List<MyPlant> myPlant = new ArrayList<MyPlant>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<PlantFollowing> plantFollowings = new ArrayList<PlantFollowing>();



    public String getUsername() {
        return null;
    }

    public Users(UsersRequestDto requestDto) {
        this.social = requestDto.getSocial();
        this.nickname = requestDto.getNickname();
    }


    public void updateProfile(String nickname){
        this.nickname = nickname;
    }


}