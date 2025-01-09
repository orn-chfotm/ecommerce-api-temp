package com.build.ecommerce.core.exception;

import com.build.ecommerce.core.error.ApplicationException;
import com.build.ecommerce.core.error.ExceptionCode;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<ExceptionResponse<Void>> handleException(Exception exception) {
        return ExceptionResponse.toResponse(ExceptionCode.EXCEPTION);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse<Void>> handleException(ApplicationException exception) {
        return ExceptionResponse.toResponse(exception.getExceptionCode());
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse<List<ValidationErrorResponse>>> handleBindValidationException(BindException exception) {
        List<ValidationErrorResponse> validErrorList = exception.getFieldErrors().stream()
                .map(fieldError -> {
                    return ValidationErrorResponse.builder()
                            .field(fieldError.getField())
                            .message(fieldError.getDefaultMessage())
                            .build();
                })
                .collect(Collectors.toList());

        return ExceptionResponse.toResponse(ExceptionCode.VALIDATION_EXCEPTION, validErrorList);
    }

    private record ValidationErrorResponse(String field, String message) {
        @Builder
        private ValidationErrorResponse {
        }
    }
}
