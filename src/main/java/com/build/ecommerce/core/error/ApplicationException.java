package com.build.ecommerce.core.error;

import lombok.Getter;

@Getter
public abstract class ApplicationException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    protected ApplicationException(String message, ExceptionCode exceptionCode) {
        super(message);
        this.exceptionCode = exceptionCode;
    }
}
