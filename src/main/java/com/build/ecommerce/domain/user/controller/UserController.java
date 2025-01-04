package com.build.ecommerce.domain.user.controller;

import com.build.ecommerce.domain.user.dto.request.UserRequest;
import com.build.ecommerce.domain.user.dto.response.UserResponse;
import com.build.ecommerce.domain.user.entity.Gender;
import com.build.ecommerce.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/v1/user")
@Tag(name = "회원", description = "회원 관련 Api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserResponse> info(Principal principal) {
        System.out.println("principal.getName() = " + principal.getName());
        return ResponseEntity.status(HttpStatus.OK)
                .body(null);
    }

    @PostMapping
    public ResponseEntity<UserResponse> signUp(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                        .body(userService.register(request));
    }
}
