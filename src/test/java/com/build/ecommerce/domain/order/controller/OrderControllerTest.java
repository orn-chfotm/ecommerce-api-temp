package com.build.ecommerce.domain.order.controller;

import com.build.ecommerce.domain.address.entity.Address;
import com.build.ecommerce.domain.address.entity.AddressEntity;
import com.build.ecommerce.domain.order.dto.reposonse.OrderDetail;
import com.build.ecommerce.domain.order.dto.reposonse.OrderRequest;
import com.build.ecommerce.domain.product.dto.request.ProductRequest;
import com.build.ecommerce.domain.product.entity.Product;
import com.build.ecommerce.domain.product.repository.ProductRepository;
import com.build.ecommerce.domain.user.dto.request.UserRequest;
import com.build.ecommerce.domain.user.entity.Gender;
import com.build.ecommerce.domain.user.entity.User;
import com.build.ecommerce.domain.user.repository.UserRepository;
import com.build.ecommerce.helper.UnitTestHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderControllerTest extends UnitTestHelper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("주문 테스트")
    void insertOrderTest() throws Exception {
        LocalDate now = LocalDate.parse(LocalDate.now().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Address address = Address.builder()
                .address("서울시")
                .extraAddress("3동")
                .zipCode("12253")
                .build();
        AddressEntity addressEntity = AddressEntity.builder()
                .address(address)
                .build();

        User user = new User("test@email.com",
                "password",
                "user",
                Gender.getByValue("W"),
                now
        );

        user.addAddr(addressEntity);
        User saveUser = userRepository.save(user);

        List<OrderDetail> orders = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Product product = ProductRequest.toEntity(new ProductRequest(
                            "fashion",
                            "장갑" + i,
                            "따뜻한 장갑",
                            BigDecimal.valueOf(100L * i),
                            10  * i,
                            1,
                            true
                    )
            );
            productRepository.save(product);
            orders.add(new OrderDetail(product.getId(), i * 10));
        }

        OrderRequest request = new OrderRequest(orders, saveUser.getId(), saveUser.getAddress().get(0).getId());

        mockMvc.perform(post("/v1/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
    
    
}