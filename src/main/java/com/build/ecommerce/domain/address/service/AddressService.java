package com.build.ecommerce.domain.address.service;

import com.build.ecommerce.domain.address.dto.request.AddressRequest;
import com.build.ecommerce.domain.address.dto.response.AddressEntityResponse;
import com.build.ecommerce.domain.address.dto.response.AddressResponse;
import com.build.ecommerce.domain.address.entity.Address;
import com.build.ecommerce.domain.address.entity.AddressEntity;
import com.build.ecommerce.domain.user.entity.User;
import com.build.ecommerce.domain.user.exception.UserNotFountException;
import com.build.ecommerce.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AddressService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public AddressResponse getAddressList(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFountException::new);

        List<AddressEntityResponse> addressEntityResponses = user.getAddressEntityList().stream()
                .map(AddressEntityResponse::toDto)
                .toList();


        return AddressResponse.toDto(addressEntityResponses);
    }

    public void registAddress(String name, AddressRequest request) {
        User user = userRepository.findByEmail(name)
                .orElseThrow(UserNotFountException::new);

        AddressEntity addressEntity = AddressEntity.builder()
                .address(AddressRequest.toEntity(request))
                .build();

        user.getAddressEntityList().add(addressEntity);
    }
}
