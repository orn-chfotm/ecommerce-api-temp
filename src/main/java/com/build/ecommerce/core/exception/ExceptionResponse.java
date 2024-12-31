package com.build.ecommerce.core.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record ExceptionResponse<T>(
        String timeStamp,
        String message,
        T detail
) {
    public static ExceptionResponse of(String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return new ExceptionResponse(LocalDateTime.now().format(formatter), message, null);
    }

    public static <T> ExceptionResponse of(String message, T detail) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return new ExceptionResponse(LocalDateTime.now().format(formatter), message, detail);
    }
}
