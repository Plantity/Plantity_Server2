package com.plantity.server.domain.myPlant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.plantity.server.domain.BaseTimeEntity;
import com.plantity.server.domain.plantlog.PlantLog;
import com.plantity.server.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
public class MyPlant extends BaseTimeEntity {
    //식물이름, 타입, 이미지, 입양날짜
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

    private String plantName;  //식물이름
    private String plantType;  //식물타입
    private String filePath;
    private Long userIdx;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String plantAdaptTime; //식물입양날짜

    @Builder
    public MyPlant(String plantName, String plantAdaptTime, String plantType, String filePath, Users users) {
        this.plantName = plantName;
        this.plantType = plantType;
        this.filePath = filePath;
        this.plantAdaptTime = plantAdaptTime;
        this.userIdx = users.getUserId();
    }
}
