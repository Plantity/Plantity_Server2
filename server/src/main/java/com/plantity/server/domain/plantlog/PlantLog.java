package com.plantity.server.domain.plantlog;

import com.plantity.server.domain.BaseTimeEntity;
import com.plantity.server.domain.myPlant.MyPlant;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "myPlantId")
    private MyPlant myPlant;

    @Embedded
    private Log log;

    public void update(Log log) {
        this.log = log;
    }

    @Builder
    public PlantLog(Long plantId, MyPlant myPlant, Log log) {
        this.plantId = plantId;
        this.myPlant = myPlant;
        this.log = log;
    }
}