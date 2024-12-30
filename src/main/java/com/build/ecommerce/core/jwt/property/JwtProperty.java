package com.build.ecommerce.core.jwt.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProperty {

    @Value("${service.jwt.key}")
    private String key;
    @Value("${service.jwt.access-expiration}")
    private String accessExpiration;
    @Value("${service.jwt.refresh-expiration}")
    private String refreshExpiration;
}
