package com.build.ecommerce.domain.product.service;

import com.build.ecommerce.domain.product.dto.request.ProductRequest;
import com.build.ecommerce.domain.product.dto.request.ProductSerchRequest;
import com.build.ecommerce.domain.product.dto.response.ProductResponse;
import com.build.ecommerce.domain.product.entity.Product;
import com.build.ecommerce.domain.product.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse insertProduct(ProductRequest productRequest) {
        Product product = productRequest.toEntity(productRequest);
        productRepository.save(product);
        return ProductResponse.toDto(product);
    }

    public List<ProductResponse> findProducts(ProductSerchRequest productSerchRequest) {
        List<Product> findProducts = productRepository.findByActiveIs(true)
                .orElse(Collections.emptyList());

        return findProducts.stream()
                .map(ProductResponse::toDto)
                .collect(Collectors.toList());
    }
}
