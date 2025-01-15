package com.build.ecommerce.core.security.login;

import com.build.ecommerce.core.jwt.JwtPayload;
import com.build.ecommerce.core.jwt.dto.request.LoginRequest;
import com.build.ecommerce.core.jwt.dto.response.TokenResponse;
import com.build.ecommerce.core.jwt.exception.AuthenticationFailException;
import com.build.ecommerce.core.jwt.service.JwtService;
import com.build.ecommerce.core.security.exception.AuthorityNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class CustomFormLoginFilter extends AbstractAuthenticationProcessingFilter {

    private final JwtService jwtService;
    private final ObjectMapper objectMapper;

    public CustomFormLoginFilter(AuthenticationManager authenticationManager,
                                 AuthenticationFailureHandler failureHandler,
                                 JwtService jwtService,
                                 ObjectMapper objectMapper) {
        super("/v1/login/admin");
        setAuthenticationManager(authenticationManager);
        setAuthenticationFailureHandler(failureHandler);
        this.jwtService = jwtService;
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!request.getMethod().equals(HttpMethod.POST.name())) {
            throw new AuthenticationFailException("요청된 메서드는 허용되지 않습니다.");
        }

        LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);

        String email = loginRequest.email();
        String password = loginRequest.password();

        if (email.isEmpty() || password.isEmpty()) {
            throw new AuthenticationFailException("ID 또는 PW를 입력해주세요.");
        }

        return getAuthenticationManager().authenticate(
                CustomFormLoginToken.toUnAuthenticate(email, password)
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        CustomFormLoginToken customToken = (CustomFormLoginToken) authResult;

        String authority = customToken.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElseThrow(AuthorityNotFoundException::new);

        TokenResponse tokenResponse = jwtService.createToken(
                new JwtPayload(customToken.getAdminId(), authority, new Date())
        );

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        new ObjectMapper().writeValue(response.getWriter(), tokenResponse);
    }
}
