
package com.build.ecommerce.domain.user.dto.response;

import com.build.ecommerce.core.util.LocalDateUtil;
import com.build.ecommerce.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.descriptor.DateTimeUtils;

public record UserResponse(
        @Schema(description = "이메일(ID)")
        String email,
        @Schema(description = "비밀번호")
        String password,
        @Schema(description = "이름")
        String name,
        @Schema(description = "성별")
        String gender,
        @Schema(description = "생년월일")
        String birthDate
) {

    public static class UserResponseBuilder {
        private String email;
        private String password;
        private String name;
        private String gender;
        private String birthDate;

        public UserResponseBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserResponseBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserResponseBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserResponseBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public UserResponseBuilder birthDate(String birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public UserResponse build() {
            return new UserResponse(this.email, this.password, this.name, this.gender, this.birthDate);
        }

        public String toString() {
            return "UserResponse.UserResponseBuilder(email=" + this.email + ", password=" + this.password + ", name=" + this.name + ", gender=" + this.gender + ", birthDate=" + this.birthDate + ")";
        }
    }

    public static UserResponseBuilder builder() {
        return new UserResponseBuilder();
    }

    public static UserResponse toDto(final User user) {
        return UserResponse.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .gender(user.getGender().getValue())
                .birthDate(LocalDateUtil.toString(user.getBirthDate()))
                .build();
    }
}
