package com.plantity.server.domain.plant.list;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PlantListResponseDto {
    private String cntntsNo;
    private String cntntsSj;

    public PlantListResponseDto(String cntntsNo, String cntntsSj) {
        this.cntntsNo = cntntsNo;
        this.cntntsSj = cntntsSj;
    }

}
