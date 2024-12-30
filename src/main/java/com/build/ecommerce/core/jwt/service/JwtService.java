package com.build.ecommerce.core.jwt.service;

import com.build.ecommerce.core.jwt.JwtDto;
import com.build.ecommerce.core.jwt.JwtProvider;
import com.build.ecommerce.core.jwt.dto.request.TokenReqeuset;
import com.build.ecommerce.core.jwt.dto.response.TokenResponse;
import com.build.ecommerce.core.jwt.property.JwtProperty;
import com.build.ecommerce.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProvider jwtProvider;
    private final JwtProperty jwtProperty;

    public TokenResponse createToken(JwtDto jwtDto) {
        String accessToken = jwtProvider.createToken(jwtDto, jwtProperty.getAccessExpiration());
        String refreshToken = jwtProvider.createToken(jwtDto, jwtProperty.getRefreshExpiration());

        return TokenResponse.toResposne(accessToken, refreshToken);
    }

}
