package com.build.client.api.order.controller;

import com.build.core.dto.response.SuccessResponse;
import com.build.domain.order.dto.reposonse.OrderRequest;
import com.build.domain.order.dto.request.OrderResponse;
import com.build.domain.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
@Tag(name = "주문", description = "주문 관련 Api")
@ApiResponse(
        responseCode = "200",
        description = "Successful",
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = OrderResponse.class)
        )
)
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @Operation(method = "GET", summary = "Select OrderedList", description = "주문 내역 List 정보를 가져온다.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "404", description = "주문자 정보를 찾을 수 없습니다.")
            }
    )
    public ResponseEntity<SuccessResponse<List<OrderResponse>>> getOrderDetails(Principal principal) {
        return SuccessResponse.toResponse(orderService.getOrderDetails(principal.getName()));
    }

    @PostMapping
    @Operation(method = "POST", summary = "Insert Order", description = "제품 목록들을 주문합니다..")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "404", description = "주문자 정보를 찾을 수 없습니다."),
                    @ApiResponse(responseCode = "400", description = "주문 제품의 정보가 잘못되었습니다.")
            }
    )
    public ResponseEntity<SuccessResponse<OrderResponse>> createOrder(@Valid @RequestBody OrderRequest request) {
        return SuccessResponse.toResponse(orderService.createOrder(request));
    }

    @PatchMapping("/{orderId}")
    @Operation(method = "PATCH", summary = "PATCH Order Status", description = "주문을 취소합니다. -> 주문 성공 상태만 취소 가능합니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "404", description = "주문 정보를 찾을 수 없습니다."),
                    @ApiResponse(responseCode = "409", description = "주문 취소 불가능 상태입니다.")
            }
    )
    public ResponseEntity<SuccessResponse<OrderResponse>> cancelOrder(@PathVariable Long orderId) {
        return SuccessResponse.toResponse(orderService.cancelOrder(orderId));
    }
}
