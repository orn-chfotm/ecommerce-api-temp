package com.build.admin.core.security.login;

import com.build.domain.member.admin.entity.Admin;
import com.build.domain.member.admin.exception.AdminNotFountException;
import com.build.domain.member.admin.repository.AdminRepository;
import com.build.domain.member.user.entity.User;
import com.build.domain.member.user.exception.UserNotFountException;
import com.build.domain.member.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(username)
                .orElseThrow(AdminNotFountException::new);

        return new CustomUserDetails(
                admin.getId(),
                admin.getEmail(),
                admin.getPassword(),
                Set.of(new SimpleGrantedAuthority(admin.getRole().name()))
        );
    }
}
