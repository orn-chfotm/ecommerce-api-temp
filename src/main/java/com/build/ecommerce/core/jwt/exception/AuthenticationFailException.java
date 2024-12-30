package com.build.ecommerce.core.jwt.exception;

import com.build.ecommerce.core.error.ApplicationException;
import com.build.ecommerce.core.error.ExceptionCode;

public class AuthenticationFailException extends ApplicationException {

    private final static ExceptionCode AUTHENTICATION_FAIL = ExceptionCode.AUTHENTICATION_FAIL;

    public AuthenticationFailException() {
        super(AUTHENTICATION_FAIL.getDefaultMessage(), AUTHENTICATION_FAIL);
    }

    public AuthenticationFailException(String message) {
        super(message, AUTHENTICATION_FAIL);
    }
}
