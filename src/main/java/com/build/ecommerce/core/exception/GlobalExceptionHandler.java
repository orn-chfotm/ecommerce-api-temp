package com.build.ecommerce.core.exception;

import com.build.ecommerce.core.error.ApplicationException;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ExceptionResponse.of("오류입니다. 다음에 시도해주세요."));
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ExceptionResponse.of("잠시후 다시 시도해주세요."));
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(ApplicationException exception) {
        return ResponseEntity.status(exception.getExceptionCode().getHttpStatus())
                .body(ExceptionResponse.of(exception.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleBindValidationException(BindException exception) {
        /*List<String> messages = exception.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();*/

        List<ValidationErrorResponse> validErrorList = new ArrayList<>();
        exception.getFieldErrors().stream()
            .forEach(fieldError -> {
                validErrorList.add(
                        ValidationErrorResponse.builder()
                                .field(fieldError.getField())
                                .message(fieldError.getDefaultMessage())
                            .build()
                );
            });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponse.of("요청 값을 확인해주세요.", validErrorList));
    }

    @Getter
    private static class ValidationErrorResponse {
        private String field;
        private String message;

        @Builder
        public ValidationErrorResponse(String field, String message) {
            this.field = field;
            this.message = message;
        }
    }
}
