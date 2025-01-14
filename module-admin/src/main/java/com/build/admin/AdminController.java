package com.build.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableJpaAuditing
@EntityScan(basePackages = "com.build.domain")
@EnableJpaRepositories(basePackages = "com.build.domain")
@SpringBootApplication(scanBasePackages = "com.build")
@EnableMethodSecurity(securedEnabled = true)
public class AdminController {

    public static void main(String[] args) {
        SpringApplication.run(AdminController.class, args);
    }
}
