package com.build.domain.member.admin.repository;

import com.build.domain.member.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByEmail(String username);

    boolean existsByEmail(String email);
}
