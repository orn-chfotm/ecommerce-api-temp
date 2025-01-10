package com.build.client.core.security.jwt;

import com.build.core.jwt.JwtPayload;
import com.build.core.jwt.service.JwtService;
import com.build.domain.member.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtAuthenticationProvider(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
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
