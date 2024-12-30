package com.build.ecommerce.domain.product.entity;

import com.build.ecommerce.domain.order.entity.Order;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;


@Entity
@Table(name = "PRODUCTS")
@Comment(value = "product information table", on = "TABLE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;

    @Comment("product category, not null")
    @Column(nullable = false)
    private Category category;

    @Comment("product name, not null")
    @Column(nullable = false)
    private String name;

    @Comment("product description")
    private String description;

    @Comment("product price, not null minimum 0")
    @Column(nullable = false)
    private BigDecimal price;

    @Comment("product stockQuantity, null is not specified / 0 is not in stock")
    private Integer stockQuantity;

    @Comment("product minimum order quantity, initial value 0")
    private int minOrderQuantity;

    @Comment("product show Active, not null")
    private boolean isActive;

    @Builder
    public Product(Category category, String name, String description, BigDecimal price, Integer stockQuantity, int minOrderQuantity, boolean isActive) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.minOrderQuantity = minOrderQuantity;
        this.isActive = isActive;
    }

}
