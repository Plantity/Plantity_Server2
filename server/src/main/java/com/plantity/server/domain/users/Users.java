package com.plantity.server.domain.users;


import com.plantity.server.domain.BaseTimeEntity;
import com.plantity.server.domain.myPlant.MyPlant;
import com.plantity.server.domain.plantFollowing.PlantFollowing;
import com.plantity.server.domain.plantlog.PlantLog;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
public class Users extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String nickName;
    private String social;
    private String rating;
    private int score;
    private String status;

    @OneToMany(mappedBy = "users")
    private List<MyPlant> myPlant = new ArrayList<MyPlant>();

    @OneToMany(mappedBy = "users")
    private List<PlantLog> plantLogs = new ArrayList<>();

    @OneToMany(mappedBy = "users")
    private List<PlantFollowing> plantFollowings = new ArrayList<PlantFollowing>();

}