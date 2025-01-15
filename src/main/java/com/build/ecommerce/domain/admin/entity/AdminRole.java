package com.build.ecommerce.domain.admin.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum AdminRole {
    ADMIN("admin"), SELLER("seller");

    private final String value;

    AdminRole(String value) {
        this.value = value;
    }

    public static AdminRole getByValue(String value) {
        return Arrays.stream(AdminRole.values())
                .filter(role -> role.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Role value is not found"));
    }
}
