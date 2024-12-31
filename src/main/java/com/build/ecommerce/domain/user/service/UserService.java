package com.build.ecommerce.domain.user.service;

import com.build.ecommerce.domain.user.dto.request.UserRequest;
import com.build.ecommerce.domain.user.dto.response.UserResponse;
import com.build.ecommerce.domain.user.entity.User;
import com.build.ecommerce.domain.user.exception.UserExistException;
import com.build.ecommerce.domain.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponse register(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.email())) {
            throw new UserExistException();
        }

        User userEntity = UserRequest.toEntity(userRequest, passwordEncoder);
        userRepository.save(userEntity);
        return UserResponse.toDto(userEntity);
    }
}
