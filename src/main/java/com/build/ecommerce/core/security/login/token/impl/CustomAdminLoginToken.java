
package com.build.ecommerce.core.security.login.token.impl;

import com.build.ecommerce.core.security.exception.AuthorityNotFoundException;
import com.build.ecommerce.core.security.login.detail.impl.CustomCommonDetails;
import com.build.ecommerce.core.security.login.token.CustomLoginToken;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class CustomAdminLoginToken extends AbstractAuthenticationToken implements CustomLoginToken {

    private final CustomCommonDetails customAdminDetails;

    private CustomAdminLoginToken(CustomCommonDetails customUserDetails,
                                  Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.customAdminDetails = customUserDetails;
    }

    public static CustomAdminLoginToken toUnAuthenticate(String username, String password) {
        return new CustomAdminLoginToken(
                new CustomCommonDetails(null, username, password, Collections.emptySet()),
                Collections.emptySet()
        );
    }

    public static CustomAdminLoginToken toAuthenticate(Long userId,
                                                       Collection<? extends GrantedAuthority> authorities) {
        return new CustomAdminLoginToken(
                new CustomCommonDetails(userId, null, null, authorities),
                authorities
        );
    }

    @Override
    public Object getPrincipal() {
        return customAdminDetails.getUsername();
    }

    @Override
    public Object getCredentials() {
        return customAdminDetails.getPassword();
    }

    @Override
    public Long getId() {
        return customAdminDetails.getId();
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
        CustomAdminLoginToken that = (CustomAdminLoginToken) object;
        return Objects.equals(customAdminDetails, that.customAdminDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customAdminDetails);
    }
}
