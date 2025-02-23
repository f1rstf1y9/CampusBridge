package com.tnasfer.gbict.global.error.exception;

import com.tnasfer.gbict.global.error.code.ExceptionCode;
import lombok.Getter;

@Getter
public class BusinessLogicException extends RuntimeException{
    private final ExceptionCode exceptionCode;

    public BusinessLogicException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

    public BusinessLogicException(String message, ExceptionCode exceptionCode) {
        super(message);
        this.exceptionCode = exceptionCode;
    }
}
