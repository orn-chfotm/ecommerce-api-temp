package com.build.client.core.security.config;

import com.build.client.core.security.jwt.JwtAuthenticationFilter;
import com.build.client.core.security.jwt.JwtAuthenticationProvider;
import com.build.client.core.security.login.CustomFormLoginFilter;
import com.build.client.core.security.login.CustomFormLoginProvider;
import com.build.core.jwt.property.JwtProperty;
import com.build.core.jwt.service.JwtService;
import com.build.core.security.handler.CustomAuthenticationEntryPoint;
import com.build.core.security.handler.CustomAuthenticationFailureHandler;
import com.build.domain.member.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final CustomAuthenticationFailureHandler authenticationFailureHandler;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;
    private final JwtService jwtService;
    private final JwtProperty jwtProperty;

    @Bean
    CustomFormLoginProvider customFormLoginProvider() {
        return new CustomFormLoginProvider(passwordEncoder, userDetailsService);
    }

    @Bean
    JwtAuthenticationProvider jwtAuthenticationProvider() {
        return new JwtAuthenticationProvider(jwtService);
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        ProviderManager authenticationManager = (ProviderManager) authenticationConfiguration.getAuthenticationManager();
        authenticationManager.getProviders().add(customFormLoginProvider());
        authenticationManager.getProviders().add(jwtAuthenticationProvider());
        return authenticationManager;
    }

    @Bean
    CustomFormLoginFilter customFormLoginFilter() throws Exception {
        return new CustomFormLoginFilter(authenticationManager(), authenticationFailureHandler, jwtService, objectMapper);
    }

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(authenticationManager(), authenticationEntryPoint, jwtProperty);
    }
}
