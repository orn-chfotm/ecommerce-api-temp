package com.build.ecommerce.domain.order.dto.reposonse;

import com.build.ecommerce.domain.order.entity.Order;
import com.build.ecommerce.domain.order.entity.OrderProduct;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record OrderRequest(
        @NotNull(message = "제품 번호가 정확하지 않습니다.")
        @Schema(name = "제품 PK")
        Long productId,
        @NotNull(message = "제품 수량을 입력해주세요")
        @Schema(name = "제품 수량")
        int quantity
) {

    public Order toEntity(OrderRequest orderRequest) {
        Order order = Order.builder().build();
        order.getOrderProducts().add(
                OrderProduct.builder()
                        .quantity(orderRequest.quantity)
                        .build()
        );

        return order;
    }
}
