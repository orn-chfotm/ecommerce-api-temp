package com.build.domain.member.admin.exception;

import com.build.core.error.ApplicationException;
import com.build.core.error.ExceptionCode;

public class AdminExistException extends ApplicationException {

    private static final ExceptionCode EXCEPTION_CODE = ExceptionCode.ADMIN_EXIST;

    public AdminExistException() {
        super(EXCEPTION_CODE);
    }

    public AdminExistException(String message) {
        super(EXCEPTION_CODE, message);
    }
}
