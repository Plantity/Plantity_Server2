package com.plantity.server.constants;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.OK;

@Getter
@AllArgsConstructor
public enum SuccessCode {
    CREATE_MYPLANT_SUCCESS(OK, "내 식물 등록하기를 성공했습니다.");

    private final HttpStatus status;
    private final String msg;
}
