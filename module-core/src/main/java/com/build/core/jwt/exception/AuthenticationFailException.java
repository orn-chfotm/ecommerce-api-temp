package com.build.core.jwt.exception;


import com.build.core.error.ExceptionCode;
import com.build.core.jwt.error.SecurityAuthenticationException;

public class AuthenticationFailException extends SecurityAuthenticationException {

    private static final ExceptionCode AUTHENTICATION_FAIL = ExceptionCode.AUTHENTICATION_NOT_FOUND;

    public AuthenticationFailException() {
        super(AUTHENTICATION_FAIL.getMessage(), AUTHENTICATION_FAIL);
    }

    public AuthenticationFailException(String message) {
        super(message, AUTHENTICATION_FAIL);
    }
}
