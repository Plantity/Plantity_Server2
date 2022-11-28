package com.plantity.server.domain.plantlog;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.plantity.server.domain.BaseTimeEntity;
import com.plantity.server.domain.myPlant.MyPlant;
import com.plantity.server.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@NoArgsConstructor
@Entity
public class PlantLog extends BaseTimeEntity {
    // Order(MyPlant, 주테이블) - Delivery(PlantLog, 대상테이블) 관계

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long plantId;

    //userId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "myPlantId")
    @JsonIgnore
    private MyPlant myPlant;

    @Embedded
    private Log log;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate logDate; //식물로그날짜

    private Long userIdx;

    @Builder
    public PlantLog(Long userIdx, Long plantId, LocalDate logDate) {
        this.userIdx = userIdx;
        this.plantId = plantId;
        //this.log = log;
        this.logDate = logDate;
    }


    public boolean waterInfo(Boolean water) {
        return log.waterInfo(water);
    }
    public boolean lookInfo(Boolean look) {
        return log.lookInfo(look);
    }
    public boolean sunInfo(Boolean sun) {
        return log.sunInfo(sun);
    }
    public boolean repotInfo(Boolean repot) {
        return log.repotInfo(repot);
    }

    public void updateRepot(Boolean repot){
        log.updateRepot(repot);
    }

    public void updateSun(Boolean sun) {
        log.updateSun(sun);
    }

    public void updateWater(Boolean water) {
        log.updateWater(water);
    }

    public void updateLook(Boolean look) {
        log.updateLook(look);
    }
}