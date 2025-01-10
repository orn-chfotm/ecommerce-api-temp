package com.build.admin.core.security.config;

import com.build.admin.core.security.handler.CustomAuthenticationFailureHandler;
import com.build.admin.core.security.jwt.JwtAuthenticationFilter;
import com.build.admin.core.security.jwt.JwtAuthenticationProvider;
import com.build.admin.core.security.login.CustomFormLoginFilter;
import com.build.admin.core.security.login.CustomFormLoginProvider;
import com.build.core.jwt.property.JwtProperty;
import com.build.core.jwt.service.JwtService;
import com.build.domain.member.admin.repository.AdminRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final ObjectMapper objectMapper;
    private final AdminRepository adminRepository;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration,
                          UserDetailsService userDetailsService,
                          JwtService jwtService,
                          ObjectMapper objectMapper,
                          AdminRepository adminRepository) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.objectMapper = objectMapper;
        this.adminRepository = adminRepository;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CustomFormLoginProvider customFormLoginProvider() {
        return new CustomFormLoginProvider(passwordEncoder(), userDetailsService);
    }

    @Bean
    JwtAuthenticationProvider jwtAuthenticationProvider() {
        return new JwtAuthenticationProvider(jwtService, adminRepository);
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        ProviderManager authenticationManager = (ProviderManager) authenticationConfiguration.getAuthenticationManager();
        authenticationManager.getProviders().add(customFormLoginProvider());
        authenticationManager.getProviders().add(jwtAuthenticationProvider());
        return authenticationManager;
    }

    @Bean
    CustomAuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    CustomFormLoginFilter customFormLoginFilter(JwtService jwtService) throws Exception {
        return new CustomFormLoginFilter(authenticationManager(), jwtService, customAuthenticationFailureHandler(), objectMapper);
    }

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter(JwtProperty jwtProperty) throws Exception {
        return new JwtAuthenticationFilter(authenticationManager(), jwtProperty);
    }
}
