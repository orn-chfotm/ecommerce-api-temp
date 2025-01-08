package com.build.ecommerce.domain.product.repository;

import com.build.ecommerce.domain.product.dto.request.ProductSerchRequest;
import com.build.ecommerce.domain.product.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductCustomRepository {

    Optional<List<Product>> customFind(ProductSerchRequest productSerchRequest);
}
