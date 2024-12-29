package com.build.ecommerce.domain.order.dto.request;

import com.build.ecommerce.domain.order.entity.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

public record OrderResponse(
        @Schema(name = "주문번호")
        Long orderNumber
) {

    public static class OrderResponseBuilder {
        private Long orderNumber;

        public OrderResponseBuilder orderNumber(Long orderNumber) {
            this.orderNumber = orderNumber;
            return this;
        }

        public OrderResponse build() {
            return new OrderResponse(orderNumber);
        }
    }

    public static OrderResponseBuilder builder() {
        return new OrderResponseBuilder();
    }

    public OrderResponse toDto(Order order) {
        return OrderResponse.builder()
                .orderNumber(order.getOrderNumber())
            .build();
    }
}
