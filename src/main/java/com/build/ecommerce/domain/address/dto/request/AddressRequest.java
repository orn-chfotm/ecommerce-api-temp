package com.build.ecommerce.domain.address.dto.request;

import com.build.ecommerce.domain.address.entity.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record AddressRequest(
        @NotNull(message = "주소지를 입력해야 합니다.")
        @Schema(description = "주소지")
        String address,

        @NotNull(message = "상세 주소지를 입력해야 합니다.")
        @Schema(description = "상세 주소")
        String extraAddress,

        @NotNull(message = "우편 번호를 입력해야합니다.")
        @Schema(description = "우편 번호")
        String zipCode
) {
        public static Address toEntity(AddressRequest request) {
                return Address.builder()
                        .address(request.address)
                        .extraAddress(request.extraAddress)
                        .zipCode(request.zipCode)
                        .build();
        }
}
