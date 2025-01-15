package com.build.ecommerce.core.security.login.detail;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomDetails extends UserDetails {

    Long getId();
}