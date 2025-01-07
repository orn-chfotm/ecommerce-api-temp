package com.build.ecommerce.domain.product.controller;

import com.build.ecommerce.core.dto.response.SuccessResponse;
import com.build.ecommerce.domain.product.dto.request.ProductRequest;
import com.build.ecommerce.domain.product.dto.request.ProductSerchRequest;
import com.build.ecommerce.domain.product.dto.response.ProductResponse;
import com.build.ecommerce.domain.product.service.ProductService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
@Tag(name = "제품", description = "제품 관련 Api")
@ApiResponse(
        responseCode = "200",
        description = "Successful",
        content = @Content(
                mediaType = "application/json"
        )
)
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<ProductResponse>> registerProduct(@Valid @RequestBody ProductRequest productRequest) {
        return SuccessResponse.toResponse(productService.insertProduct(productRequest));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<ProductResponse>>> getProductList(@Valid @RequestBody ProductSerchRequest productSerchRequest) {
        return SuccessResponse.toResponse(productService.findProducts(productSerchRequest));
    }
}
