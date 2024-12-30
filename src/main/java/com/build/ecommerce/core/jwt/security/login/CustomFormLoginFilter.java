package com.build.ecommerce.core.jwt.security.login;

import com.build.ecommerce.core.jwt.JwtDto;
import com.build.ecommerce.core.jwt.dto.response.TokenResponse;
import com.build.ecommerce.core.jwt.exception.AuthenticationFailException;
import com.build.ecommerce.core.jwt.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;
import java.util.Date;

@Configuration
public class CustomFormLoginFilter extends AbstractAuthenticationProcessingFilter {

    private final JwtService jwtService;

    public CustomFormLoginFilter(AuthenticationManager authenticationManager, JwtService jwtService) {
        super("/v1/login");
        setAuthenticationManager(authenticationManager);
        this.jwtService = jwtService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws org.springframework.security.core.AuthenticationException, IOException, ServletException {
        if (!request.getMethod().equals(HttpMethod.POST.name())) {
            throw new AuthenticationFailException();
        }

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email.isBlank() || password.isBlank()) {
            throw new AuthenticationFailException();
        }

        return getAuthenticationManager().authenticate(
                CustomFormLoginToken.toUnAuthenticate(email, password)
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String email = (String) authResult.getPrincipal();
        TokenResponse tokenResponse = jwtService.createToken(new JwtDto(email, new Date()));
        response.setHeader("access-token", tokenResponse.accessToken());
        response.setHeader("refresh-token" ,tokenResponse.refreshToken());
    }
}
