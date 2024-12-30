package com.build.ecommerce.core.jwt.security.login;

import com.build.ecommerce.core.jwt.exception.AuthenticationMethodException;
import com.build.ecommerce.core.jwt.service.JwtService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;

@Configuration
public class CustomFormLoginFilter extends AbstractAuthenticationProcessingFilter {

    public CustomFormLoginFilter(AuthenticationManager authenticationManager, JwtService jwtService) {
        super("/v1/login");
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (!request.getMethod().equals(HttpMethod.POST.name())) {
            throw new AuthenticationMethodException();
        }


    }
}
