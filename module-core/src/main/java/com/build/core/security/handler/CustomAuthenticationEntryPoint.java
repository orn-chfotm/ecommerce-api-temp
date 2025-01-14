package com.build.core.security.handler;

import com.build.core.dto.response.FailResponse;
import com.build.core.error.ExceptionCode;
import com.build.core.util.CustomHandlerUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        ExceptionCode exceptionCode = CustomHandlerUtil.defineException(exception);
        CustomHandlerUtil.toResponse(response, exceptionCode.getHttpStatus());
        new ObjectMapper().writeValue(response.getWriter(), FailResponse.toResponse(exceptionCode));
    }
}
