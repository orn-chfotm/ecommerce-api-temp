package com.build.domain.member.user.service;

import com.build.domain.member.user.dto.request.UserRequest;
import com.build.domain.member.user.dto.response.UserResponse;
import com.build.domain.member.user.entity.User;
import com.build.domain.member.user.exception.UserExistException;
import com.build.domain.member.user.exception.UserNotFountException;
import com.build.domain.member.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public UserResponse getUserDetail(Long userId) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(UserNotFountException::new);
        return UserResponse.toDto(findUser);
    }

    public UserResponse registerUser(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.email())) {
            throw new UserExistException();
        }

        User userEntity = UserRequest.toEntity(userRequest, passwordEncoder);
        userRepository.save(userEntity);
        return UserResponse.toDto(userEntity);
    }
}
