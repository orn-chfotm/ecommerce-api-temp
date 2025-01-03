package com.build.ecommerce.core.security;

import com.build.ecommerce.core.jwt.security.jwt.JwtAuthenticationFilter;
import com.build.ecommerce.core.jwt.security.login.CustomFormLoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;

@Configuration
public class SecurityFilterConfig {

    private final CustomFormLoginFilter customFormLoginFilter;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityFilterConfig(CustomFormLoginFilter customFormLoginFilter, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.customFormLoginFilter = customFormLoginFilter;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    SecurityFilterChain http(HttpSecurity http) throws Exception {
        http
            .httpBasic(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(sessionConfig ->
                    sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        http
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/v1/signUp").permitAll();
            })
            .headers(header -> {
               header.frameOptions().disable();
            });

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(customFormLoginFilter, JwtAuthenticationFilter.class);

        return http.build();
    }

}
