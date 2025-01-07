package com.build.ecommerce.core.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCode {

    // Exception Defualt
    EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "잠시후 다시 시도해주세요."),
    VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "요청 값을 확인해주세요."),

    // Security Exception
    AUTHENTICATION_FAIL(HttpStatus.UNAUTHORIZED, "인증 정보를 찾을 수 없습니다."),

    // 사용자
    USER_EXIST(HttpStatus.BAD_REQUEST, "이미 존재하는 사용자입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자 정보를 찾을 수 없습니다."),

    // 제품
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "제품 정보를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    ExceptionCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
