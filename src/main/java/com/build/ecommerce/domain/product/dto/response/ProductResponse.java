package com.build.ecommerce.domain.product.dto.response;

import com.build.ecommerce.domain.product.entity.Category;
import com.build.ecommerce.domain.product.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record ProductResponse(
        @Schema(description = "제품 카테고리")
        String category,
        @Schema(description = "제품명")
        String name,
        @Schema(description = "제품 설명")
        String description,
        @Schema(description = "제품 가격")
        BigDecimal price,
        @Schema(description = "제품 재고 수량")
        Integer stockQuantity,
        @Schema(description = "최소 주문 수량")
        int minOrderQuantity,
        @Schema(description = "제품 노출 여부")
        boolean isActive
) {

    public static class ProductResponseBuilder {
        private String category;
        private String name;
        private String description;
        private BigDecimal price;
        private Integer stockQuantity;
        private int minOrderQuantity;
        private boolean isActive;

        public ProductResponseBuilder category(String category) {
            this.category = category;
            return this;
        }

        public ProductResponseBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProductResponseBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProductResponseBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ProductResponseBuilder stockQuantity(Integer stockQuantity) {
            this.stockQuantity = stockQuantity;
            return this;
        }

        public ProductResponseBuilder minOrderQuantity(int minOrderQuantity) {
            this.minOrderQuantity = minOrderQuantity;
            return this;
        }

        public ProductResponseBuilder isActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public ProductResponse build() {
            return new ProductResponse(category, name, description, price, stockQuantity, minOrderQuantity, isActive);
        }
    }

    public static ProductResponseBuilder builder() {
        return new ProductResponseBuilder();
    }

    public ProductResponse toDto(Product product) {
        return ProductResponse.builder()
                .category(product.getCategory().getValue())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .minOrderQuantity(product.getMinOrderQuantity())
                .isActive(product.isActive())
            .build();
    }
}
