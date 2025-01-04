package com.build.ecommerce.domain.address.dto.response;

import com.build.ecommerce.domain.address.entity.Address;
import com.build.ecommerce.domain.address.entity.AddressEntity;
import io.swagger.v3.oas.annotations.media.Schema;

public record AddressResponse(
        @Schema(description = "주소지")
        String address,
        @Schema(description = "상세 주소지")
        String extraAddress,
        @Schema(description = "우편 번호")
        String zipCode
) {

    public static class AddressResponseBuilder {
        private String address;
        private String extraAddress;
        private String zipCode;

        public AddressResponseBuilder address(String address) {
            this.address = address;
            return this;
        }

        public AddressResponseBuilder extraAddress(String extraAddress) {
            this.extraAddress = extraAddress;
            return this;
        }

        public AddressResponseBuilder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public AddressResponse build() {
            return new AddressResponse(address, extraAddress, zipCode);
        }

        @Override
        public String toString() {
            return "AddressResponse.AddressResponseBuilder(address= " + this.address + ", extraAddress=" + this.extraAddress + ", zipCode=" + this.zipCode + ")";
        }
    }

    public static AddressResponseBuilder builder() {
        return new AddressResponseBuilder();
    }

    public static AddressResponse toDto(AddressEntity addressEntity) {
        Address address = addressEntity.getAddress();
        return AddressResponse.builder()
                .address(address.getAddress())
                .extraAddress(address.getExtraAddress())
                .zipCode(address.getZipCode())
                .build();
    }
}
