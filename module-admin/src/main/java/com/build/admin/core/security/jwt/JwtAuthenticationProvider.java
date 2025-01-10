package com.build.admin.core.security.jwt;

import com.build.core.jwt.JwtPayload;
import com.build.core.jwt.service.JwtService;
import com.build.domain.member.admin.entity.Admin;
import com.build.domain.member.admin.exception.AdminNotFountException;
import com.build.domain.member.admin.repository.AdminRepository;
import com.build.domain.member.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtService jwtService;
    private final AdminRepository adminRepository;

    public JwtAuthenticationProvider(JwtService jwtService,
                                     AdminRepository adminRepository) {
        this.jwtService = jwtService;
        this.adminRepository = adminRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String jwtToken = (String) authentication.getCredentials();

        JwtPayload jwtDto = jwtService.verifyToken(jwtToken);

        Admin findAdmin = adminRepository.findByEmail(jwtDto.email())
                .orElseThrow(AdminNotFountException::new);

        return JwtAuthenticationToken.toAuthenticate(findAdmin);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(JwtAuthenticationToken.class);
    }
}
