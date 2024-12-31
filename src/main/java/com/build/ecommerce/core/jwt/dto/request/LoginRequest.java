package com.build.ecommerce.core.jwt.dto.request;

public record LoginRequest(
        String email,
        String password
) {
}
