package com.build.ecommerce.domain.order.entity;

import com.build.ecommerce.domain.product.entity.Product;
import com.build.ecommerce.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;

@Entity
@Table(name = "ORDER_PRODUCT")
@Comment(value = "Order Product relation table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_PRODUCT_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Comment(value = "order total price")
    private BigDecimal totalPrice;

    @Comment(value = "order product quantity")
    private int quantity;

    @Builder
    public OrderProduct(Order order, Product product, BigDecimal totalPrice, int quantity) {
        this.order = order;
        this.product = product;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
    }

    public void setOder(Order order) {
        this.order = order;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
