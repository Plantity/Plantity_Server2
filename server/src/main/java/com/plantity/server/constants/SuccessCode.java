package com.plantity.server.constants;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.OK;

@Getter
@AllArgsConstructor
public enum SuccessCode {
    CREATE_MYPLANT_SUCCESS(OK, "내 식물 등록하기를 성공했습니다."),
    UPDATE_SUNPLANTLOG_SUCCESS(OK, "광합성 로그 등록하기를 성공했습니다."),
    UPDATE_REPOTPLANTLOG_SUCCESS(OK, "분갈이 로그 등록하기를 성공했습니다."),
    UPDATE_WATERPLANTLOG_SUCCESS(OK, "물주기 로그 등록하기를 성공했습니다."),
    UPDATE_LOOKPLANTLOG_SUCCESS(OK, "관찰 로그 등록하기를 성공했습니다."),

    USER_INFO_SUCCESS(OK, "유저 프로필 상세 조회를 성공했습니다."),
    MYPLANT_INFO_SUCCESS(OK, "나의 식물 상세 조회를 성공했습니다."),
    PLANTLOG_INFO_SUCCESS(OK, "식물 로그 상세 조회를 성공했습니다.");

    private final HttpStatus status;
    private final String msg;
}
