package com.build.ecommerce.core.jwt.security.login;

import com.build.ecommerce.domain.user.entity.User;
import com.build.ecommerce.domain.user.exception.UserNotFountException;
import com.build.ecommerce.domain.user.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;

public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFountException());

        return new CustomUserDetails(
                user.getEmail(),
                user.getPassword(),
                Set.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
