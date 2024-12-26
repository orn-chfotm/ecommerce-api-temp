package com.build.ecommerce.domain.user.controller;

import com.build.ecommerce.domain.user.dto.request.UserRequest;
import com.build.ecommerce.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@Tag(name = "회원", description = "회원 관련 Api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public void signUp(@Valid @RequestBody UserRequest request) {
        userService.register(request);
    }
}
