package com.build.ecommerce.core.jwt;

import com.build.ecommerce.core.jwt.property.JwtProperty;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Objects;

@Component
public class JwtProvider {

    private final SecretKey secretKey;
    @Value("${spring.application.name}")
    private String issuer;

    public JwtProvider(JwtProperty jwtProperty) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtProperty.getKey()));
    }

    public String createToken(JwtDto jwtDto, long expiration) {
        return Jwts.builder()
                .claim("user-key", Objects.requireNonNull(jwtDto.email()))
                .issuer(issuer)
                .issuedAt(Objects.requireNonNull(jwtDto.issuedAt()))
                .expiration(new Date(jwtDto.issuedAt().getTime() + expiration))
                .signWith(secretKey, Jwts.SIG.HS256)
            .compact();
    }
}
