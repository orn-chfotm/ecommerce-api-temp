package com.build.client.core.security.jwt;

import com.build.core.jwt.error.SecurityAuthenticationException;
import com.build.core.jwt.exception.AuthenticationFailException;
import com.build.core.jwt.property.JwtProperty;
import com.build.core.security.handler.CustomAuthenticationEntryPoint;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final JwtProperty jwtProperty;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if (!hasJwtToken(request)) {
                filterChain.doFilter(request, response);
                return;
            }

            String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION).substring(jwtProperty.getTokenType().length() + 1);

            SecurityContextHolder.getContext()
                    .setAuthentication(authenticationManager.authenticate(
                            JwtAuthenticationToken.toUnAuthenticate(accessToken)
                    ));

            filterChain.doFilter(request, response);
        } catch (SecurityAuthenticationException e) {
            SecurityContextHolder.clearContext();
            authenticationEntryPoint.commence(request, response, e);
        }
    }

    private boolean hasJwtToken(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        return header != null && header.startsWith(jwtProperty.getTokenType() + " ");
    }

    /*@Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        if (request.getMethod().equals(HttpMethod.POST.name())) {
            List<String> excludePath = Arrays.asList("/v1/user", "/v1/login/client");
            return excludePath.contains(request.getRequestURI());
        }
        return super.shouldNotFilter(request);
    }*/
}
