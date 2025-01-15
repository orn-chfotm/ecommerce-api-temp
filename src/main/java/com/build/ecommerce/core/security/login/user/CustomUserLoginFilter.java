package com.build.ecommerce.core.security.login.user;

import com.build.ecommerce.core.dto.response.ValidationErrorResponse;
import com.build.ecommerce.core.jwt.JwtPayload;
import com.build.ecommerce.core.jwt.dto.response.TokenResponse;
import com.build.ecommerce.core.security.exception.AuthenticationFailException;
import com.build.ecommerce.core.security.exception.AuthorityNotFoundException;
import com.build.ecommerce.core.security.login.dto.request.LoginRequest;
import com.build.ecommerce.core.security.login.token.CustomLoginToken;
import com.build.ecommerce.core.security.login.token.impl.CustomUserLoginToken;
import com.build.ecommerce.core.security.login.util.FilterUtil;
import com.build.ecommerce.domain.admin.dto.response.AdminResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomUserLoginFilter extends AbstractAuthenticationProcessingFilter {

    /* Filter Setting */
    private final static String LOGIN_REQUEST_URL = "/v1/login/user";


    private final Validator validator;

    public CustomUserLoginFilter(Validator validator) {
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
                CustomUserLoginToken.toUnAuthenticate(
                        loginRequest.email(),
                        loginRequest.password()
                )
        );
    }
}
