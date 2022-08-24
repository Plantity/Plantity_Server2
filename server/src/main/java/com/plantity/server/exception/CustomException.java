package com.plantity.server.exception;

import com.plantity.server.constants.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{
    private final ExceptionCode exceptionCode;
}
