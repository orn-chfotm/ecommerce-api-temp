package com.build.domain.address.exception;

import com.build.core.error.ApplicationException;
import com.build.core.error.ExceptionCode;

public class AddressNotFountException extends ApplicationException {

    private static final ExceptionCode EXCEPTION_CODE = ExceptionCode.ADDRESS_NOT_FOUND;

    public AddressNotFountException() {
        super(EXCEPTION_CODE);
    }

    public AddressNotFountException(String message) {
        super(EXCEPTION_CODE, message);
    }
}
