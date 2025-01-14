package com.build.core.jwt.exception;


import com.build.core.error.ExceptionCode;
import com.build.core.jwt.error.SecurityAuthenticationException;

public class AuthorityNotFoundException extends SecurityAuthenticationException {

    private static final ExceptionCode AUTHENTICATION_FAIL = ExceptionCode.AUTHENTICATION_UNAUTHORIZED;

    public AuthorityNotFoundException() {
        super(AUTHENTICATION_FAIL.getMessage(), AUTHENTICATION_FAIL);
    }

    public AuthorityNotFoundException(String message) {
        super(message, AUTHENTICATION_FAIL);
    }
}
