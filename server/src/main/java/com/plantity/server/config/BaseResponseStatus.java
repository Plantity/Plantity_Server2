package com.plantity.server.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),
    POST_SUCCESS(true, 1001, "식물 등록에 성공하였습니다"),

    /**
     * 2000: Request 오류
     */
    // post
    POST_CNTNTSNO_INVALID(false, 2001, "cntntsNo가 유효하지 않습니다"),

    // user
    USER_ID_INVALID(false, 2010, "해당 유저가 없습니다"),

    // myPlant
    MYPLANT_ID_INVALID(false, 2015, "해당 식물이 없습니다");

    /**
     * 3000: Response 오류
     */



    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
