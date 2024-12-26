package com.build.ecommerce.domain.user.dto.request;

import com.build.ecommerce.core.util.LocalDateUtil;
import com.build.ecommerce.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record UserRequest(
        @NotNull(message = "이메일을 입력해야 합니다.")
        @Schema(description = "이메일(ID)")
        String email,
        @NotNull(message = "비밀번호를 입력해야 합니다.")
        @Schema(description = "비밀번호")
        String password,
        @NotNull(message = "이름을 입력해야 합니다.")
        @Schema(description = "이름")
        String name,
        @NotNull(message = "성별을 입력해야 합니다.")
        @Schema(description = "성별")
        String gender,
        @NotNull(message = "생년월일을 입력해야 합니다.")
        @Schema(description = "생년월일")
        String birthDate
) {

    public static User to(UserRequest userRequest) {
        return User.builder()
                .email(userRequest.email)
                .password(userRequest.password)
                .name(userRequest.name)
                .birthDate(LocalDateUtil.toLocalDate(userRequest.birthDate))
                .build();
    }
}
