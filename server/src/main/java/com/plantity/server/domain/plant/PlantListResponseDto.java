package com.plantity.server.domain.plant;

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
    /*
    private String rtnFileSeCode;
    private String rtnFileSn;
    private String rtnOrginlFileNm;
    private String rtnStreFileNm;
    private String rtnFileCours;
    private String rtnImageDc;
    private String rtnThumbFileNm;
    private String rtnImgSeCode;

    public PlantListResponseDto(String cntntsNo, String cntntsSj, String rtnFileSeCode, String rtnFileSn, String rtnOrginlFileNm, String rtnStreFileNm, String rtnFileCours, String rtnImageDc, String rtnThumbFileNm, String rtnImgSeCode) {
        this.cntntsNo = cntntsNo;
        this.cntntsSj = cntntsSj;
        this.rtnFileSeCode = rtnFileSeCode;
        this.rtnFileSn = rtnFileSn;
        this.rtnOrginlFileNm = rtnOrginlFileNm;
        this.rtnStreFileNm = rtnStreFileNm;
        this.rtnFileCours = rtnFileCours;
        this.rtnImageDc = rtnImageDc;
        this.rtnThumbFileNm = rtnThumbFileNm;
        this.rtnImgSeCode = rtnImgSeCode;
    }

     */
}
