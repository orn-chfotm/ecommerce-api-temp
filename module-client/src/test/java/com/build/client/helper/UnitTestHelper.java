package com.build.client.helper;

import com.build.client.core.security.TestSecurityConfig;
import com.build.domain.member.user.dto.request.UserRequest;
import com.build.domain.member.user.dto.response.UserResponse;
import com.build.domain.member.user.entity.User;
import com.build.domain.member.user.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Import(TestSecurityConfig.class)
public class UnitTestHelper {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    protected static String accessToken;
    protected static String refreshToken;

    @BeforeAll
    @DisplayName("유저 생성 및 Token 발급")
    public void createUser() throws Exception {
        String email = "test@email.com";
        String password = "testPassword";
        UserRequest userRequest = new UserRequest(
                email,
                password,
                "testUser",
                "M",
                "2024-01-08"
        );

        mockMvc.perform(post("/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest))
                )
                .andDo(print());

        MvcResult result = mockMvc.perform(post("/v1/login/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new LoginDto(email, password)))
                )
                .andDo(print())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);
        accessToken = jsonNode.get("accessToken").asText();
        refreshToken = jsonNode.get("refreshToken").asText();
    }

    protected HttpHeaders getHeaderSetting() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }

    protected HttpHeaders getAccessToken() {
        String sendAccessToken = "Bearer " + accessToken;

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, sendAccessToken);

        return headers;
    }

    private static class LoginDto{
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public LoginDto(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }
}
