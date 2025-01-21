package com.build.ecommerce.api.client;

import com.build.ecommerce.core.dto.response.SuccessResponse;
import com.build.ecommerce.domain.product.dto.request.ProductRequest;
import com.build.ecommerce.domain.product.dto.request.ProductSerchRequest;
import com.build.ecommerce.domain.product.dto.response.ProductResponse;
import com.build.ecommerce.domain.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/manager/product")
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
public class ClientProductController {

    private final ProductService productService;

    @GetMapping
    @Operation(method = "GET", summary = "Select Prodcut Infomation", description = "제품 정보를 검색합니다.")
    public ResponseEntity<SuccessResponse<List<ProductResponse>>> getProductDetail(@Valid @RequestBody ProductSerchRequest serchRequest) {
        return SuccessResponse.toResponse(productService.getProductDetail(serchRequest));
    }
}
