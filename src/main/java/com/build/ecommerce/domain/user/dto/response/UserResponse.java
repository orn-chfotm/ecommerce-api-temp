
package com.build.ecommerce.domain.user.dto.response;

import com.build.ecommerce.core.util.LocalDateUtil;
import com.build.ecommerce.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;

public record UserResponse(
        @Schema(description = "User table PK")
        Long id,
        @Schema(description = "이메일(ID)")
        String email,
        @Schema(description = "이름")
        String name,
        @Schema(description = "성별")
        String gender,
        @Schema(description = "생년월일")
        String birthDate
) {

    public static class UserResponseBuilder {
        private Long id;
        private String email;
        private String name;
        private String gender;
        private String birthDate;

        public UserResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserResponseBuilder email(String email) {
            this.email = email;
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
            return new UserResponse(this.id, this.email, this.name, this.gender, this.birthDate);
        }

        @Override
        public String toString() {
            return "UserResponseBuilder{" +
                    "id=" + id +
                    ", email='" + email + '\'' +
                    ", name='" + name + '\'' +
                    ", gender='" + gender + '\'' +
                    ", birthDate='" + birthDate + '\'' +
                    '}';
        }
    }

    public static UserResponseBuilder builder() {
        return new UserResponseBuilder();
    }

    public static UserResponse toDto(final User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .gender(user.getGender().getValue())
                .birthDate(LocalDateUtil.toString(user.getBirthDate()))
            .build();
    }
}
