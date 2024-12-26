package com.build.ecommerce.core.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse<String>> handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionResponse<>(LocalDateTime.now(), "오류입니다. 다음에 시도해주세요."));
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse<String>> handleException(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionResponse<>(LocalDateTime.now(), "잠시후 다시 시도해주세요."));
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse<List<String>>> handleBindValidationException(BindException exception) {
        List<String> messages = exception.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse<>(LocalDateTime.now(), messages));
    }
}
