package com.build.domain.order.exception;

import com.build.core.error.ApplicationException;
import com.build.core.error.ExceptionCode;

public class OrderNotFountException extends ApplicationException {

    private static final ExceptionCode EXCEPTION_CODE = ExceptionCode.ORDER_NOT_FOUND;

    public OrderNotFountException() {
        super(EXCEPTION_CODE);
    }

    public OrderNotFountException(String message) {
        super(EXCEPTION_CODE, message);
    }
}
