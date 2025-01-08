package com.build.ecommerce.domain.user.controller;

import com.build.ecommerce.domain.user.dto.request.UserRequest;
import com.build.ecommerce.helper.UnitTestHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends UnitTestHelper {

    @Test
    @DisplayName("회원가입")
    void signUpTest() throws Exception {
        UserRequest userRequest = new UserRequest(
                "test@email.com",
                "testPassword",
                "testUser",
                "M",
                "2024-01-08"
        );

        mockMvc.perform(post("/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest))
                ).andDo(print())
                .andExpect(status().isOk());

    }
}