package com.build.ecommerce.domain.product.repository;

import com.build.ecommerce.domain.product.entity.Product;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public class ProductCustomRespositoryImpl implements ProductCustomRepository{

    private final JPQLQueryFactory queryFactory;

    public ProductCustomRespositoryImpl(JPQLQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Optional<List<Product>> productSearch() {
        return Optional.empty();
    }
}
