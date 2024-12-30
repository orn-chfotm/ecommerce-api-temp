package com.build.ecommerce.core.jwt.security.login;

import io.jsonwebtoken.lang.Collections;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Objects;

public class CustomFormLoginToken extends AbstractAuthenticationToken {

    private final CustomUserDetails customUserDetails;

    private CustomFormLoginToken(CustomUserDetails customUserDetails,
                                 Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.customUserDetails = customUserDetails;
    }

    public static CustomFormLoginToken toUnAuthenticate(String username, String password) {
        return new CustomFormLoginToken(
                new CustomUserDetails(username, password, Collections.emptySet()),
                Collections.emptySet()
        );
    }

    public static CustomFormLoginToken toAuthenticate(String username,
                                                       Collection<? extends GrantedAuthority> authorities) {
        return new CustomFormLoginToken(
                new CustomUserDetails(username, null, authorities),
                authorities
        );
    }

    @Override
    public Object getPrincipal() {
        return customUserDetails.getUsername();
    }

    @Override
    public Object getCredentials() {
        return customUserDetails.getPassword();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        CustomFormLoginToken that = (CustomFormLoginToken) object;
        return Objects.equals(customUserDetails, that.customUserDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customUserDetails);
    }
}
