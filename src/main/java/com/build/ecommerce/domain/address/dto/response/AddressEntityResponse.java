package com.build.ecommerce.domain.address.dto.response;

import com.build.ecommerce.domain.address.entity.Address;
import com.build.ecommerce.domain.address.entity.AddressEntity;
import io.swagger.v3.oas.annotations.media.Schema;

public record AddressEntityResponse(
        @Schema(description = "주소지")
        String address,
        @Schema(description = "상세 주소지")
        String extraAddress,
        @Schema(description = "우편 번호")
        String zipCode
) {
    public static class AddressEntityResponseBuilder {
        private String address;
        private String extraAddress;
        private String zipCode;

        public AddressEntityResponseBuilder address(String address) {
            this.address = address;
            return this;
        }

        public AddressEntityResponseBuilder extraAddress(String extraAddress) {
            this.extraAddress = extraAddress;
            return this;
        }

        public AddressEntityResponseBuilder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public AddressEntityResponse build() {
            return new AddressEntityResponse(address, extraAddress, zipCode);
        }

        @Override
        public String toString() {
            return "AddressResponse.AddressResponseBuilder(address= " + this.address + ", extraAddress=" + this.extraAddress + ", zipCode=" + this.zipCode + ")";
        }
    }

    public static AddressEntityResponseBuilder builder() {
        return new AddressEntityResponseBuilder();
    }

    public static AddressEntityResponse toDto(AddressEntity addressEntity) {
        Address address = addressEntity.getAddress();
        return AddressEntityResponse.builder()
                .address(address.getAddress())
                .extraAddress(address.getExtraAddress())
                .zipCode(address.getZipCode())
                .build();
    }
}
