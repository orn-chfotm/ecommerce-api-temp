package com.build.ecommerce.core.jwt.security.jwt;

import com.build.ecommerce.core.jwt.JwtPayload;
import com.build.ecommerce.core.jwt.service.JwtService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtService jwtService;

    public JwtAuthenticationProvider(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String jwtToken = (String) authentication.getCredentials();

        JwtPayload jwtDto = jwtService.verifyToken(jwtToken);

        return JwtAuthenticationToken.toAuthenticate(jwtDto.email());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(JwtAuthenticationToken.class);
    }
}
