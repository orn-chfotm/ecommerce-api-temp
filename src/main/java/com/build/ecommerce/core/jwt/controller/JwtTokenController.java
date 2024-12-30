package com.build.ecommerce.core.jwt.controller;

import com.build.ecommerce.core.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JwtTokenController {

    private final JwtService jwtService;


}
