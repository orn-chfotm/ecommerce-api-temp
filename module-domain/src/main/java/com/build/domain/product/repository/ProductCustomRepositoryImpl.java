package com.build.domain.product.repository;

import com.build.domain.product.dto.request.ProductSerchRequest;
import com.build.domain.product.entity.Product;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.build.domain.product.entity.QProduct.product;


@Repository
public class ProductCustomRepositoryImpl implements ProductCustomRepository {

    private final JPAQueryFactory queryFactory;

    public ProductCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Optional<List<Product>> customFind(ProductSerchRequest productSerchRequest) {
        return Optional.ofNullable(
                queryFactory.selectFrom(product)
                .where(
                        product.category.in(productSerchRequest.getCategory()),
                        product.name.like("% " + productSerchRequest.name() + " %"),
                        product.price.between(productSerchRequest.minPrice(), productSerchRequest.maxPrice()),
                        product.stockQuantity.gt(productSerchRequest.stockQuantity())
                )
                .fetch()
        );
    }
}
