package com.build.core.dto.response;

import com.build.core.dto.BaseResponse;
import com.build.core.error.ExceptionCode;
import com.build.core.util.LocaDateTimeUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public record FailResponse<T> (
        @Schema(description = "요청시간")
        String timestamp,
        @Schema(description = "상태코드")
        Integer status,
        @Schema(description = "상세 메세지")
        String message,
        @Schema(description = "상세 데이터")
        T data
) implements BaseResponse {

    public static FailResponse<Void> of(final String message) {
        return new FailResponse<>(
                LocaDateTimeUtil.nowToString(),
                null,
                message,
                null
        );
    }

    public static <T> ResponseEntity<FailResponse<Void>> toResponse(@NotNull final ExceptionCode exceptionCode) {
        HttpStatus httpStatus = exceptionCode.getHttpStatus();
        return ResponseEntity.status(exceptionCode.getHttpStatus())
                .body(new FailResponse<>(
                        LocaDateTimeUtil.nowToString(),
                        httpStatus.value(),
                        exceptionCode.getMessage(),
                        null
                ));
    }

    public static <T> ResponseEntity<FailResponse<T>> toResponse(@NotNull final ExceptionCode exceptionCode,
                                                                 final T data) {
        HttpStatus httpStatus = exceptionCode.getHttpStatus();
        return ResponseEntity.status(exceptionCode.getHttpStatus())
                .body(new FailResponse<>(
                        LocaDateTimeUtil.nowToString(),
                        httpStatus.value(),
                        exceptionCode.getMessage(),
                        data
                ));
    }
}
