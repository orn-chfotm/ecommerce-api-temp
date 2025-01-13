package com.build.core.jwt;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

public record JwtPayload(
        @Schema(description = "회원 PK")
        Long userId,
        @Schema(description = "인증 일시")
        Date issuedAt
) {

}
