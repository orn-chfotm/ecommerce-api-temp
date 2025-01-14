package com.build.core.dto.response;

import com.build.core.dto.BaseResponse;
import com.build.core.util.LocaDateTimeUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public record SuccessResponse<T> (
        @Schema(description = "요청시간")
        String timestamp,
        @Schema(description = "상태코드")
        Integer status,
        @Schema(description = "상세 메세지")
        String message,
        @Schema(description = "상세 데이터")
        T data
) implements BaseResponse {
    public static <T> ResponseEntity<SuccessResponse<T>> toResponse(final T data) {
        final HttpStatus statusOk = HttpStatus.OK;
        return ResponseEntity.status(statusOk)
            .contentType(MediaType.APPLICATION_JSON)
            .body(new SuccessResponse<>(
                    LocaDateTimeUtil.nowToString(),
                    statusOk.value(),
                    statusOk.getReasonPhrase(),
                    data
            ));
    }
}
