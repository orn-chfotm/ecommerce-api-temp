package com.build.admin.core.security.jwt;

import com.build.domain.member.admin.entity.Admin;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final String principal;
    private final String credentials;
    private final boolean isAuthenticated;

    private JwtAuthenticationToken(String principal, String authority, String credentials, boolean isAuthenticated) {
        super(Set.of(new SimpleGrantedAuthority(authority)));
        this.principal = principal;
        this.credentials = credentials;
        this.isAuthenticated = isAuthenticated;
    }

    public static Authentication toUnAuthenticate(String accessToken) {
        return new JwtAuthenticationToken(null, null, accessToken, false);
    }

    public static Authentication toAuthenticate(Admin admin) {
        return new JwtAuthenticationToken(admin.getEmail(), admin.getRole().name(), null, true);
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }
}
