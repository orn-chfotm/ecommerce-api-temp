package com.build.core.jwt.controller;

import com.build.core.dto.response.SuccessResponse;
import com.build.core.jwt.dto.request.TokenReqeuset;
import com.build.core.jwt.dto.response.TokenResponse;
import com.build.core.jwt.exception.AuthenticationFailException;
import com.build.core.jwt.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/token")
@RequiredArgsConstructor
@Tag(name = "JWT Token", description = "JWT 토큰 관련 API")
@ApiResponse(
        responseCode = "200",
        description = "Successful",
        content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = TokenResponse.class)
        )
)
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
