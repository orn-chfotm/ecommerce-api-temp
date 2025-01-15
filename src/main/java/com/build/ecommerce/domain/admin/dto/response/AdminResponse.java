package com.build.ecommerce.domain.admin.dto.response;

import com.build.ecommerce.domain.admin.entity.Admin;
import io.swagger.v3.oas.annotations.media.Schema;

public record AdminResponse(
        @Schema(description = "관리자 email(ID)")
        String email,
        @Schema(description = "관리자 이름")
        String name,
        @Schema(description = "관리자 권한")
        String role
) {

    private static class AdminResponseBuilder {
        private String email;
        private String role;
        private String name;

        private AdminResponseBuilder email(String email) {
            this.email = email;
            return this;
        }

        private AdminResponseBuilder role(String role) {
            this.role = role;
            return this;
        }

        private AdminResponseBuilder name(String name) {
            this.name = name;
             return this;
        }

        private AdminResponse build() {
            return new AdminResponse(email,name, role);
        }
    }

    public static AdminResponseBuilder builder() {
        return new AdminResponseBuilder();
    }

    public static AdminResponse toDto(Admin admin) {
        return AdminResponse.builder()
                .email(admin.getEmail())
                .name(admin.getName())
                .role(admin.getRole().name())
                .build();
    }
}
