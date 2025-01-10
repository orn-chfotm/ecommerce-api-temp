package com.build.core.jwt.service;

import com.build.core.jwt.JwtPayload;
import com.build.core.jwt.JwtProvider;
import com.build.core.jwt.dto.response.TokenResponse;
import com.build.core.jwt.property.JwtProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProvider jwtProvider;
    private final JwtProperty jwtProperty;

    public TokenResponse createToken(JwtPayload jwtPayload) {
        String accessToken = jwtProvider.createToken(jwtPayload, jwtProperty.getAccessExpiration());
        String refreshToken = jwtProvider.createToken(jwtPayload, jwtProperty.getRefreshExpiration());

        return TokenResponse.toResposne(accessToken, refreshToken);
    }

    public JwtPayload verifyToken(String jwtToken) {
        return jwtProvider.verifyToken(jwtToken);
    }

}
