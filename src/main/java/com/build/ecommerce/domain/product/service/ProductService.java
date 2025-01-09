package com.build.ecommerce.domain.product.service;

import com.build.ecommerce.domain.product.dto.request.ProductRequest;
import com.build.ecommerce.domain.product.dto.request.ProductSerchRequest;
import com.build.ecommerce.domain.product.dto.response.ProductResponse;
import com.build.ecommerce.domain.product.entity.Category;
import com.build.ecommerce.domain.product.entity.Product;
import com.build.ecommerce.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse insertProduct(ProductRequest productRequest) {
        Product product = ProductRequest.toEntity(productRequest);
        productRepository.save(product);
        return ProductResponse.toDto(product);
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> findProducts(ProductSerchRequest productSerchRequest) {
        List<Product> findProducts = productRepository.searchProducts(
                productSerchRequest.getCategory(),
                productSerchRequest.name(),
                productSerchRequest.minPrice(),
                productSerchRequest.maxPrice(),
                productSerchRequest.stockQuantity()
        );

        return findProducts.stream()
                .map(ProductResponse::toDto)
                .collect(Collectors.toList());
    }
}
