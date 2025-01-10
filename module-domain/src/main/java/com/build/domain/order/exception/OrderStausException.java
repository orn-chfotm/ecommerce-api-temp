package com.build.domain.order.exception;

import com.build.core.error.ApplicationException;
import com.build.core.error.ExceptionCode;

public class OrderStausException extends ApplicationException {

    private static final ExceptionCode EXCEPTION_CODE = ExceptionCode.ORDER_NOT_CANCLE;

    public OrderStausException() {
        super(EXCEPTION_CODE);
    }

    public OrderStausException(String message) {
        super(EXCEPTION_CODE, message);
    }
}
