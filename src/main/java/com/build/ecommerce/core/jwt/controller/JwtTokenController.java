package com.build.ecommerce.core.jwt.controller;

import com.build.ecommerce.core.dto.response.SuccessResponse;
import com.build.ecommerce.core.jwt.dto.request.TokenReqeuset;
import com.build.ecommerce.core.jwt.dto.response.TokenResponse;
import com.build.ecommerce.core.jwt.exception.AuthenticationFailException;
import com.build.ecommerce.core.jwt.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JwtTokenController {

    private final JwtService jwtService;

    @PostMapping("/client")
    @Operation(method = "POST", summary = "get new Token", description = "refresh 토큰을 사용한 Token 재발급")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "401", description = "토큰 인증 실패",
                            content = @Content(schema = @Schema(implementation = AuthenticationFailException.class))
                    )
            }
    )
    public ResponseEntity<SuccessResponse<TokenResponse>> getTokenRefresh(@Valid @RequestBody TokenReqeuset reqeuset) {
        return SuccessResponse.toResponse(jwtService.getTokenRefresh(reqeuset));
    }
}
