package com.build.ecommerce.core.dto.address;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddressRequest {

    @NotNull(message = "로그인이 필요합니다.")
    private Long id;

    @NotNull(message = "주소를 입력해주세요.")
    private String city;
    @NotNull(message = "상세 주소를 입력해주세요.")
    private String street;
    @NotNull(message = "우편번호를 입력해주세요.")
    private String zipCode;
}
