package com.plantity.server.constants;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@AllArgsConstructor
public enum ExceptionCode {

    /* 400 - 잘못된 요청 */
    NO_REQUIRED_PARAMETER(BAD_REQUEST, "필수 요청 파라미터 값이 없습니다");

    /* 401 - 인증 실패 */
    // token 관련

    /* 403 - 허용되지 않은 접근 */

    /* 404 - 찾을 수 없는 리소스 */

    /* 409 - 중복된 리소스 */

    private final HttpStatus status;
    private final String msg;
}
