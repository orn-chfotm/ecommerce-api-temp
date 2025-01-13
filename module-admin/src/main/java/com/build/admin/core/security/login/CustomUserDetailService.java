package com.build.admin.core.security.login;

import com.build.domain.member.user.entity.User;
import com.build.domain.member.user.exception.UserNotFountException;
import com.build.domain.member.user.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(UserNotFountException::new);

        return new CustomUserDetails(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                Set.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
