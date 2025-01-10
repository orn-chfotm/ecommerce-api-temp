package com.build.client.core.security.handler;

import com.build.core.error.ExceptionCode;
import com.build.core.dto.response.FailResponse;
import com.build.core.jwt.error.SecurityAuthenticationException;
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

        SecurityAuthenticationException securityException = (SecurityAuthenticationException) exception;
        ExceptionCode exceptionCode = ((SecurityAuthenticationException) exception).getExceptionCode();
        String message = securityException.getMessage();

        response.setStatus(exceptionCode.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        FailResponse exceptionResponse = FailResponse.of(message);
        new ObjectMapper().writeValue(response.getWriter(), exceptionResponse);
    }
}
