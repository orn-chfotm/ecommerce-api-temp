package com.build.ecommerce.core.exception;

import java.time.LocalDateTime;

public record ExceptionResponse<T>(
        LocalDateTime timeStamp,
        T message
) {
}
