package com.build.ecommerce.domain.user.controller;

import com.build.ecommerce.core.dto.response.SuccessResponse;
import com.build.ecommerce.core.jwt.security.jwt.JwtAuthenticationToken;
import com.build.ecommerce.domain.user.dto.request.UserRequest;
import com.build.ecommerce.domain.user.dto.response.UserResponse;
import com.build.ecommerce.domain.user.entity.Gender;
import com.build.ecommerce.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/v1/user")
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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{email}")
    public ResponseEntity<SuccessResponse<UserResponse>> info(@PathVariable String email) {
        return SuccessResponse.toResponse(userService.getDetail(email));
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<UserResponse>> signUp(@Valid @RequestBody UserRequest request) {
        return SuccessResponse.toResponse(userService.register(request));
    }
}
