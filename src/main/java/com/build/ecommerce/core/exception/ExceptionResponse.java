package com.build.ecommerce.core.exception;

import com.build.ecommerce.core.error.ExceptionCode;
import com.build.ecommerce.core.util.LocaDateTimeUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;

public record ExceptionResponse<T> (
        @Schema(description = "요청 시간")
        String timeStamp,
        @Schema(description = "상세 메세지")
        String message,
        @Schema(description = "상세 데이터")
        T data
) {

    public static ExceptionResponse<Void> of(final String message) {
        return new ExceptionResponse<>(
                LocaDateTimeUtil.nowToString(),
                message,
                null
        );
    }

    public static <T> ResponseEntity<ExceptionResponse<Void>> toResponse(@NotNull final ExceptionCode exceptionCode) {
        return ResponseEntity.status(exceptionCode.getHttpStatus())
            .body(new ExceptionResponse<>(
                    LocaDateTimeUtil.nowToString(),
                    exceptionCode.getMessage(),
                    null
            ));
    }

    public static <T> ResponseEntity<ExceptionResponse<T>> toResponse(@NotNull final ExceptionCode exceptionCode,
                                                                      final T data) {
        return ResponseEntity.status(exceptionCode.getHttpStatus())
            .body(new ExceptionResponse<>(
                    LocaDateTimeUtil.nowToString(),
                    exceptionCode.getMessage(),
                    data
            ));
    }
}
