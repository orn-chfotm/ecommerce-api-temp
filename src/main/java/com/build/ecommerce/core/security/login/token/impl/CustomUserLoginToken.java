
package com.build.ecommerce.core.security.login.token.impl;

import com.build.ecommerce.core.security.exception.AuthorityNotFoundException;
import com.build.ecommerce.core.security.login.detail.impl.CustomCommonDetails;
import com.build.ecommerce.core.security.login.token.CustomLoginToken;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class CustomUserLoginToken extends AbstractAuthenticationToken implements CustomLoginToken {

    private final CustomCommonDetails customUserDetails;

    private CustomUserLoginToken(CustomCommonDetails customUserDetails,
                                 Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.customUserDetails = customUserDetails;
    }

    public static CustomUserLoginToken toUnAuthenticate(String username, String password) {
        return new CustomUserLoginToken(
                new CustomCommonDetails(null, username, password, Collections.emptySet()),
                Collections.emptySet()
        );
    }

    public static CustomUserLoginToken toAuthenticate(Long userId,
                                                      Collection<? extends GrantedAuthority> authorities) {
        return new CustomUserLoginToken(
                new CustomCommonDetails(userId, null, null, authorities),
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
    public Long getId() {
        return customUserDetails.getId();
    }

    @Override
    public String getRole() {
        return getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElseThrow(AuthorityNotFoundException::new);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        CustomUserLoginToken that = (CustomUserLoginToken) object;
        return Objects.equals(customUserDetails, that.customUserDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customUserDetails);
    }
}
