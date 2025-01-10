package com.build.core.dto.response;

import com.build.core.error.ExceptionCode;
import com.build.core.util.LocaDateTimeUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;

public record FailResponse<T> (
        @Schema(description = "요청 시간")
        String timeStamp,
        @Schema(description = "상세 메세지")
        String message,
        @Schema(description = "상세 데이터")
        T data
) {

    public static FailResponse<Void> of(final String message) {
        return new FailResponse<>(
                LocaDateTimeUtil.nowToString(),
                message,
                null
        );
    }

    public static <T> ResponseEntity<FailResponse<Void>> toResponse(@NotNull final ExceptionCode exceptionCode) {
        return ResponseEntity.status(exceptionCode.getHttpStatus())
            .body(new FailResponse<>(
                    LocaDateTimeUtil.nowToString(),
                    exceptionCode.getMessage(),
                    null
            ));
    }

    public static <T> ResponseEntity<FailResponse<T>> toResponse(@NotNull final ExceptionCode exceptionCode,
                                                                 final T data) {
        return ResponseEntity.status(exceptionCode.getHttpStatus())
            .body(new FailResponse<>(
                    LocaDateTimeUtil.nowToString(),
                    exceptionCode.getMessage(),
                    data
            ));
    }
}
