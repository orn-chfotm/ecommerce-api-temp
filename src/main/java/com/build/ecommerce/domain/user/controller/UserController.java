package com.build.ecommerce.domain.user.controller;

import com.build.ecommerce.core.dto.response.SuccessResponse;
import com.build.ecommerce.core.jwt.security.jwt.JwtAuthenticationToken;
import com.build.ecommerce.domain.user.dto.request.UserRequest;
import com.build.ecommerce.domain.user.dto.response.UserResponse;
import com.build.ecommerce.domain.user.entity.Gender;
import com.build.ecommerce.domain.user.exception.UserExistException;
import com.build.ecommerce.domain.user.exception.UserNotFountException;
import com.build.ecommerce.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
@Tag(name = "회원", description = "회원 관련 Api")
@ApiResponse(
        responseCode = "200",
        description = "Successful",
        content = @Content(
                mediaType = "application/json"
        )
)
public class UserController {

    private final UserService userService;

    @GetMapping("/{email}")
    @Operation(method = "GET", summary = "Select User Infomation", description = "사용자 정보를 검색합니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "404", description = "사용자 정보를 찾을 수 없습니다.",
                            content = @Content(schema = @Schema(implementation = UserNotFountException.class))
                    )
            }
    )
    public ResponseEntity<SuccessResponse<UserResponse>> info(@PathVariable String email) {
        return SuccessResponse.toResponse(userService.getDetail(email));
    }

    @PostMapping
    @Operation(method = "POST", summary = "Insert User", description = "회원 가입을 진행합니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "400", description = "이미 존재하는 사용자입니다.",
                            content = @Content(schema = @Schema(implementation = UserExistException.class))
                    )
            }
    )
    public ResponseEntity<SuccessResponse<UserResponse>> signUp(@Valid @RequestBody UserRequest request) {
        return SuccessResponse.toResponse(userService.register(request));
    }
}
