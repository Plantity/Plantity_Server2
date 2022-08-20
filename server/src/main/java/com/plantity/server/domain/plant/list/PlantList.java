package com.plantity.server.domain.plant.list;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Entity
@Getter
public class PlantList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long plantListId;

    private String cntntsNo;
    private String cntntsSj;

    public PlantList(PlantListResponseDto plantListResponseDto){
        this.cntntsNo = plantListResponseDto.getCntntsNo();
        this.cntntsSj = plantListResponseDto.getCntntsSj();

    }
}
