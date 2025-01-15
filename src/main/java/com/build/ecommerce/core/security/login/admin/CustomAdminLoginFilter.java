package com.build.ecommerce.core.security.login.admin;

import com.build.ecommerce.core.dto.response.ValidationErrorResponse;
import com.build.ecommerce.core.security.exception.AuthenticationFailException;
import com.build.ecommerce.core.security.login.dto.request.LoginRequest;
import com.build.ecommerce.core.security.login.token.impl.CustomAdminLoginToken;
import com.build.ecommerce.core.security.login.util.FilterUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class CustomAdminLoginFilter extends AbstractAuthenticationProcessingFilter {


    private final static String LOGIN_REQUEST_URL = "/v1/login/admin";
    private final Validator validator;

    public CustomAdminLoginFilter(Validator validator) {
        super(FilterUtil.getRequestMatcher(LOGIN_REQUEST_URL));
        this.validator = validator;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!FilterUtil.isApplicationJson(request.getContentType())) {
                throw new AuthenticationFailException("지원하지 않는 Content-Type 입니다.");
        }

        LoginRequest loginRequest = LoginRequest.parseDto(request, validator);

        return getAuthenticationManager().authenticate(
                CustomAdminLoginToken.toUnAuthenticate(
                        loginRequest.email(),
                        loginRequest.password()
                )
        );
    }
}
