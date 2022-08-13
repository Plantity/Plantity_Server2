package com.plantity.server.domain.plantlog;

import com.plantity.server.domain.myPlant.MyPlant;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Getter
public class PlantLog {
    // Order(MyPlant, 주테이블) - Delivery(PlantLog, 대상테이블) 관계

    @Id @GeneratedValue
    private Long plantId;

    private String test;

    public PlantLog(String test){
        this.test = test;
    }

    /*
    @Id @GeneratedValue
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "plantLog", fetch = FetchType.LAZY)
    private MyPlant myPlant;

    @Embedded
    private Log log;

     */
}