package com.plantity.server.dto;

import com.plantity.server.constants.ExceptionCode;
import com.plantity.server.constants.SuccessCode;
import com.plantity.server.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class BaseResponse {

    private Boolean success;
    private String msg;
    private LocalDateTime timestamp = LocalDateTime.now();

    public BaseResponse(Boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public static BaseResponse of(Boolean success, String msg) {
        return new BaseResponse(success, msg);
    }

    public static ResponseEntity<BaseResponse> toCustomErrorResponse(
            ExceptionCode exceptionCode) {
        return ResponseEntity
                .status(exceptionCode.getStatus())
                .body(BaseResponse.of(false, exceptionCode.getMsg()));
    }

    public static ResponseEntity<BaseResponse> toBasicErrorResponse(HttpStatus status, String msg) {
        return ResponseEntity
                .status(status)
                .body(BaseResponse.of(false, msg));
    }

    public static ResponseEntity<BaseResponse> toSuccessResponse(SuccessCode successCode) {
        return ResponseEntity
                .status(successCode.getStatus())
                .body(BaseResponse.of(true, successCode.getMsg()));
    }
}
