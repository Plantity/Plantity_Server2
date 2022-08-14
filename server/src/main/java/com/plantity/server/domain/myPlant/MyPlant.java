package com.plantity.server.domain.myPlant;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Entity
@Getter
public class MyPlant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long myPlantId;

    private String plantName;
    private String plantType;
    private int level;
    private String content;
    private String plantImage;
    private String status;
}
