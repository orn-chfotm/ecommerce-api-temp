package com.build.domain.member.user.exception;

import com.build.core.error.ApplicationException;
import com.build.core.error.ExceptionCode;

import static com.build.core.error.ExceptionCode.USER_EXIST;

public class UserExistException extends ApplicationException {

    private static final ExceptionCode EXCEPTION_CODE = USER_EXIST;

    public UserExistException() {
        super(EXCEPTION_CODE);
    }

    public UserExistException(String message) {
        super(EXCEPTION_CODE, message);
    }
}
