package com.build.client.api.user.controller;

import com.build.client.helper.UnitTestHelper;
import com.build.domain.member.user.dto.request.UserRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

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