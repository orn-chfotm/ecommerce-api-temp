package com.build.ecommerce.core.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCode {

    // 사용자
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자 정보를 찾을 수 없습니다."),

    // 제품
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "제품 정보를 찾을 수 없습니다."),

    // Authentication
    AUTHENTICATION_FAIL(HttpStatus.UNAUTHORIZED, "접근 정보가 잘못되었습니다.");

    private final HttpStatus httpStatus;
    private final String defaultMessage;

    ExceptionCode(HttpStatus httpStatus, String defaultMessage) {
        this.httpStatus = httpStatus;
        this.defaultMessage = defaultMessage;
    }
}
