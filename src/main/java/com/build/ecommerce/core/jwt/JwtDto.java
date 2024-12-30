package com.build.ecommerce.core.jwt;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Date;

public record JwtDto(
        @Schema(description = "인증 ID")
        String email,
        @Schema(description = "인증 일시")
        Date issuedAt
) {

}
