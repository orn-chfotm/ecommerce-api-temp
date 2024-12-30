package com.build.ecommerce.core.jwt.exception;

import com.build.ecommerce.core.error.ApplicationException;
import com.build.ecommerce.core.error.ExceptionCode;

public class AuthenticationMethodException extends ApplicationException {

    private final static ExceptionCode AUTH_NOT_ALLOW = ExceptionCode.AUTHENTICATION_FAIL;

    public AuthenticationMethodException() {
        super(AUTH_NOT_ALLOW.getDefaultMessage(), AUTH_NOT_ALLOW);
    }
}
