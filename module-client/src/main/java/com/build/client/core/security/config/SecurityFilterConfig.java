package com.build.client.core.security.config;

import com.build.client.core.security.jwt.JwtAuthenticationFilter;
import com.build.client.core.security.login.CustomFormLoginFilter;
import com.build.core.security.handler.CustomAccessDeniedHandler;
import com.build.core.security.handler.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityFilterConfig {

    private final CustomFormLoginFilter customFormLoginFilter;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;

    @Bean
    SecurityFilterChain http(HttpSecurity http) throws Exception {
        http
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionConfig ->
                        sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .headers(header -> {
                    header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable);
                })
                .authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers(HttpMethod.POST, "/v1/user", "/v1/login/client").permitAll()
                            .requestMatchers("/h2-console/**", "/swagger-ui/**", "/swagger/**", "/swagger-resources/**", "/v3/**").permitAll()
                            .anyRequest().authenticated();
                })
                .exceptionHandling(exceptionConfigurer -> {
                    exceptionConfigurer.authenticationEntryPoint(authenticationEntryPoint);
                    exceptionConfigurer.accessDeniedHandler(accessDeniedHandler);
                })
        ;

        /* JWT 인증, Custom Form 로그인 Filter 등록 */
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(customFormLoginFilter, JwtAuthenticationFilter.class);

        return http.build();
    }

}
