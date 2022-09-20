package com.plantity.server.domain.plantlog;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.plantity.server.domain.BaseTimeEntity;
import com.plantity.server.domain.myPlant.MyPlant;
import com.plantity.server.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


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

    @Builder
    public PlantLog(Long plantId, MyPlant myPlant, Log log) {
        this.plantId = plantId;
        this.myPlant = myPlant;
        this.log = log;
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