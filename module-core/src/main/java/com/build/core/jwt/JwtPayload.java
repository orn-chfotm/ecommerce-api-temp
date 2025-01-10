package com.build.core.jwt;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

public record JwtPayload(
        @Schema(description = "인증 ID")
        String email,
        @Schema(description = "인증 일시")
        Date issuedAt
) {

}
