package com.build.ecommerce.domain.product.controller;

import com.build.ecommerce.core.security.TestSecurityConfig;
import com.build.ecommerce.domain.product.dto.request.ProductRequest;
import com.build.ecommerce.domain.product.dto.request.ProductSerchRequest;
import com.build.ecommerce.domain.product.entity.Product;
import com.build.ecommerce.domain.product.repository.ProductRepository;
import com.build.ecommerce.helper.UnitTestHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductControllerTest extends UnitTestHelper {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("제품 등록")
    void productInsertTest() throws Exception {
        ProductRequest request = new ProductRequest(
                "fashion",
                "장갑",
                "따뜻한 장갑",
                BigDecimal.valueOf(10000L),
                100,
                1,
                true
        );

        mockMvc.perform(post("/v1/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("제품 등록 실패")
    void productInsertFailTest() throws Exception {
        // Given -> 사용자의 값 누락
        ProductRequest userRequestFail = new ProductRequest(
                "",
                "장갑",
                "따뜻한 장갑",
                BigDecimal.valueOf(10000L),
                100,
                1,
                true
        );

        // Given -> fashion 이 맞는 Enum 값 FE Option 값 에러 500으로 처리
        ProductRequest frontRequestFail = new ProductRequest(
                "fasion",
                "장갑",
                "따뜻한 장갑",
                BigDecimal.valueOf(10000L),
                100,
                1,
                true
        );

        // when & then
        mockMvc.perform(post("/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestFail)))
                .andDo(print())
                .andExpect(status().is4xxClientError());

        mockMvc.perform(post("/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(frontRequestFail)))
                .andDo(print())
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("제품 리스트 GET")
    void productListTest() throws Exception {

        ProductRequest request = new ProductRequest(
                "fashion",
                "장갑",
                "따뜻한 장갑",
                BigDecimal.valueOf(10000L),
                100,
                1,
                true
        );

        for (int i = 0; i < 10; i++) {
            Product product = request.toEntity(request);
            productRepository.save(product);
        }

        // given
        ProductSerchRequest serchRequest = new ProductSerchRequest(
                null,
                null,
                0,
                100000,
                0
        );

        // when & then
        mockMvc.perform(get("/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(serchRequest)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}