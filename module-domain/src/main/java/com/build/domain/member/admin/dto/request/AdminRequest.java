package com.build.domain.member.admin.dto.request;

import com.build.domain.member.admin.entity.Admin;
import com.build.domain.member.admin.entity.AdminRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdminRequest(
        @NotBlank(message = "이메일을 입력해야 합니다.")
        @Schema(description = "관리자 email(ID)")
        String email,
        @NotBlank(message = "비밀번호를 입력해야 합니다.")
        @Schema(description = "관리자 PW")
        String password,
        @NotBlank(message = "이름을 입력해야 합니다.")
        @Schema(description = "관리자 이름")
        String name,
        @NotNull(message = "다시 시도해주세요")
        @Schema(description = "관리자 권한")
        String role
) {

    public static Admin toEntity(AdminRequest request) {
        return Admin.builder()
                .email(request.email())
                .password(request.password)
                .name(request.name)
                .role(AdminRole.getByValue(request.role))
                .build();
    }
}
