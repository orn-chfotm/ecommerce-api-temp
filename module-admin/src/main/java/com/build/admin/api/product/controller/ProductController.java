package com.build.admin.api.product.controller;

import com.build.core.dto.response.SuccessResponse;
import com.build.domain.product.dto.request.ProductRequest;
import com.build.domain.product.dto.request.ProductSerchRequest;
import com.build.domain.product.dto.response.ProductResponse;
import com.build.domain.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
@Tag(name = "제품", description = "제품 관련 Api")
@ApiResponse(
        responseCode = "200",
        description = "Successful",
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ProductResponse.class)
        )
)
@Secured({"ROLE_ADMIN", "ROLE_SELLER"})
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @Operation(method = "POST", summary = "Insert Prodcut", description = "제품을 등록합니다.")
    public ResponseEntity<SuccessResponse<ProductResponse>> registerProduct(@Valid @RequestBody ProductRequest request) {
        return SuccessResponse.toResponse(productService.insertProduct(request));
    }
}
