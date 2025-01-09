package com.build.ecommerce.domain.address.exception;

import com.build.ecommerce.core.error.ApplicationException;
import com.build.ecommerce.core.error.ExceptionCode;

public class AddressNotFountException extends ApplicationException {

    private static final ExceptionCode EXCEPTION_CODE = ExceptionCode.ADDRESS_NOT_FOUND;

    public AddressNotFountException() {
        super(EXCEPTION_CODE);
    }

    public AddressNotFountException(String message) {
        super(EXCEPTION_CODE, message);
    }
}
