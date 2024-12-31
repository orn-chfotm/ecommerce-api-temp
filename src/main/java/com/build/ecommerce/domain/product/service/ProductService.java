package com.build.ecommerce.domain.product.service;

import com.build.ecommerce.domain.product.dto.request.ProductRequest;
import com.build.ecommerce.domain.product.dto.response.ProductResponse;
import com.build.ecommerce.domain.product.entity.Product;
import com.build.ecommerce.domain.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
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
}
