package com.build.ecommerce.domain.order.controller;

import com.build.ecommerce.core.dto.response.SuccessResponse;
import com.build.ecommerce.domain.order.dto.reposonse.OrderRequest;
import com.build.ecommerce.domain.order.dto.request.OrderResponse;
import com.build.ecommerce.domain.order.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/order")
@Tag(name = "주문", description = "주문 관련 Api")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<OrderResponse>> order(@Valid @RequestBody OrderRequest request) {
        return SuccessResponse.toResponse(orderService.createOrder(request));
    }
}
