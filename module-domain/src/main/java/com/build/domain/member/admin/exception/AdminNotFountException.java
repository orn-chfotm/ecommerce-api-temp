package com.build.domain.member.admin.exception;

import com.build.core.error.ApplicationException;
import com.build.core.error.ExceptionCode;

public class AdminNotFountException extends ApplicationException {

    private static final ExceptionCode EXCEPTION_CODE = ExceptionCode.ADMIN_NOT_FOUND;

    public AdminNotFountException() {
        super(EXCEPTION_CODE);
    }

    public AdminNotFountException(String message) {
        super(EXCEPTION_CODE, message);
    }
}
