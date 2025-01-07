package com.build.ecommerce.domain.user.exception;

import com.build.ecommerce.core.error.ApplicationException;
import com.build.ecommerce.core.error.ExceptionCode;

import static com.build.ecommerce.core.error.ExceptionCode.USER_EXIST;
import static com.build.ecommerce.core.error.ExceptionCode.USER_NOT_FOUND;

public class UserExistException extends ApplicationException {

    private final static ExceptionCode EXCEPTION_CODE = USER_EXIST;

    public UserExistException() {
        super(EXCEPTION_CODE.getMessage(), EXCEPTION_CODE);
    }

    public UserExistException(String message) {
        super(message, EXCEPTION_CODE);
    }
}
