package com.build.ecommerce.core.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record ExceptionResponse(
        String timeStamp,
        String message
) {
    public static ExceptionResponse of(String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return new ExceptionResponse(LocalDateTime.now().format(formatter), message);
    }
}
