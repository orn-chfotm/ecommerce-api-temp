package com.build.core.jwt.dto.request;

public record LoginRequest(
        String email,
        String password
) {
}
