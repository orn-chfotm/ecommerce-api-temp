package com.build.ecommerce.domain.product.exception;

import com.build.ecommerce.core.error.ApplicationException;
import com.build.ecommerce.core.error.ExceptionCode;

import static com.build.ecommerce.core.error.ExceptionCode.PRODUCT_NOT_FOUND;

public class ProductNotFountException extends ApplicationException {

    private static final ExceptionCode EXCEPTION_CODE = PRODUCT_NOT_FOUND;

    public ProductNotFountException() {
        super(EXCEPTION_CODE);
    }

    public ProductNotFountException(String message) {
        super(EXCEPTION_CODE, message);
    }
}
