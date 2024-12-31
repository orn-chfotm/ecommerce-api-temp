package com.build.ecommerce.core.jwt.security.handler;

import com.build.ecommerce.core.error.ExceptionCode;
import com.build.ecommerce.core.exception.ExceptionResponse;
import com.build.ecommerce.core.jwt.error.SecurityException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        SecurityException securityException = (SecurityException) exception;
        ExceptionCode exceptionCode = securityException.getExceptionCode();
        String message = securityException.getMessage();

        response.setStatus(exceptionCode.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        ExceptionResponse exceptionResponse = ExceptionResponse.of(message);
        new ObjectMapper().writeValue(response.getWriter(), exceptionResponse);
    }
}
