package com.build.domain.product.service;

import com.build.domain.product.dto.request.ProductRequest;
import com.build.domain.product.dto.request.ProductSerchRequest;
import com.build.domain.product.dto.response.ProductResponse;
import com.build.domain.product.entity.Product;
import com.build.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse insertProduct(ProductRequest request) {
        Product product = ProductRequest.toEntity(request);
        productRepository.save(product);
        return ProductResponse.toDto(product);
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> getProductDetail(ProductSerchRequest serchRequest) {
        List<Product> findProducts = productRepository.searchProducts(
                serchRequest.getCategory(),
                serchRequest.name(),
                serchRequest.minPrice(),
                serchRequest.maxPrice(),
                serchRequest.stockQuantity()
        );

        return findProducts.stream()
                .map(ProductResponse::toDto)
                .collect(Collectors.toList());
    }
}
