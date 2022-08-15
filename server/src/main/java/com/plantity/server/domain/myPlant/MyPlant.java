package com.plantity.server.domain.myPlant;

import com.plantity.server.domain.BaseTimeEntity;
import com.plantity.server.domain.plantlog.PlantLog;
import com.plantity.server.domain.users.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
public class MyPlant extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long myPlantId;

    // userId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Users users;

    // logId
    @OneToMany(mappedBy = "myPlant")
    private List<PlantLog> plantLog = new ArrayList<PlantLog>();

    private String plantName;
    private String plantType;
    private int level;
    private String content;
    private String plantImage;
    private String status;
}
