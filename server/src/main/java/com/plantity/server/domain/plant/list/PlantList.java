package com.plantity.server.domain.plant.list;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
public class PlantList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int plantListId;

    private String cntntsNo;
    private String cntntsSj;

    public PlantList(PlantListResponseDto plantListResponseDto){
        this.cntntsNo = plantListResponseDto.getCntntsNo();
        this.cntntsSj = plantListResponseDto.getCntntsSj();

    }
}
