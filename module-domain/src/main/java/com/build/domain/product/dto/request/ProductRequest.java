package com.build.domain.product.dto.request;

import com.build.domain.product.entity.Category;
import com.build.domain.product.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "제품 카테고리를 입력해야 합니다.")
        @Schema(description = "제품 카테고리")
        String category,
        @NotBlank(message = "제품명을 입력해야 합니다.")
        @Schema(description = "제품명")
        String name,
        @Schema(description = "제품 설명")
        String description,
        @NotNull(message = "제품 가격을 입력해야 합니다.")
        @Min(value = 0, message = "제품 가격은 0원 이상을 입력해야 합니다. ")
        @Schema(description = "제품 가격")
        BigDecimal price,
        @Schema(description = "제품 재고 수량")
        Integer stockQuantity,
        @Min(value = 0, message = "최소 주문 수량은 0 이상을 입력해야 합니다.")
        @Schema(description = "최소 주문 수량")
        int minOrderQuantity,
        @NotNull(message = "노출 여부를 선택해주세요.")
        @Schema(description = "제품 노출 여부")
        boolean active
) {
    public static Product toEntity(final ProductRequest productRequest) {
        return Product.builder()
                .category(Category.getByValue(productRequest.category))
                .name(productRequest.name)
                .description(productRequest.description)
                .price(productRequest.price)
                .stockQuantity(productRequest.stockQuantity)
                .minOrderQuantity(productRequest.minOrderQuantity)
                .active(productRequest.active)
            .build();
    }
}
