package com.build.domain.address.dto.response;

import java.util.List;

public record AddressResponse(
        List<AddressEntityResponse> addressList
) {

    private static class AddressResponseBuilder {
        private List<AddressEntityResponse> addressList;

        private AddressResponseBuilder addressList(List<AddressEntityResponse> addressList) {
            this.addressList = addressList;
            return this;
        }

        private AddressResponse build() {
            return new AddressResponse(addressList);
        }
    }

    public static AddressResponseBuilder builder() {
        return new AddressResponseBuilder();
    }

    public static AddressResponse toDto(List<AddressEntityResponse> addressEntityResponse) {
        return AddressResponse.builder()
                .addressList(addressEntityResponse)
                .build();
    }
}
