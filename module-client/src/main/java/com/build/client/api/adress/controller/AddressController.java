package com.build.client.api.adress.controller;

import com.build.core.dto.response.SuccessResponse;
import com.build.domain.address.dto.request.AddressRequest;
import com.build.domain.address.dto.response.AddressResponse;
import com.build.domain.address.service.AddressService;
import com.build.domain.member.user.exception.UserNotFountException;
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

@RestController
@RequestMapping("/v1/address")
@RequiredArgsConstructor
@Tag(name = "배송지", description = "배송지 관련 Api")
@ApiResponses(
        value = {
                @ApiResponse(responseCode = "200",
                        description = "Successful",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = AddressResponse.class)
                        )
                ),
                @ApiResponse(responseCode = "404", description = "사용자 정보를 찾을 수 없습니다.",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = UserNotFountException.class)
                        )
                )
        }
)
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    @Operation(method = "GET", summary = "get Address List", description = "사용자의 배송지 등록 정보를 조회합니다.")
    public ResponseEntity<SuccessResponse<AddressResponse>> getAddressList(Principal principal) {
        return SuccessResponse.toResponse(addressService.getAddressList(principal.getName()));
    }

    @PostMapping
    @Operation(method = "POST", summary = "register Address", description = "사용자의 배송지를 등록합니다.")
    ResponseEntity<SuccessResponse<Void>> registAddress(Principal principal,
                                                                   @Valid @RequestBody AddressRequest request) {
        addressService.registAddress(principal.getName(), request);
        return SuccessResponse.toResponse(null);
    }
}
