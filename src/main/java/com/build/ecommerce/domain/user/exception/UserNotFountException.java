package com.build.ecommerce.domain.user.exception;

import com.build.ecommerce.core.error.ApplicationException;
import com.build.ecommerce.core.error.ExceptionCode;

import static com.build.ecommerce.core.error.ExceptionCode.USER_NOT_FOUND;

public class UserNotFountException extends ApplicationException {

    private static final ExceptionCode EXCEPTION_CODE = USER_NOT_FOUND;

    public UserNotFountException() {
        super(EXCEPTION_CODE);
    }

    public UserNotFountException(String message) {
        super(EXCEPTION_CODE, message);
    }
}
