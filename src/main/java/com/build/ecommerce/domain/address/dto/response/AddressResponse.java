package com.build.ecommerce.domain.address.dto.response;

import com.build.ecommerce.domain.address.entity.Address;
import com.build.ecommerce.domain.address.entity.AddressEntity;
import io.swagger.v3.oas.annotations.media.Schema;

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
