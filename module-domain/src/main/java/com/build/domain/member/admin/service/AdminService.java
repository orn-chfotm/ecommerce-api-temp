package com.build.domain.member.admin.service;

import com.build.domain.member.admin.dto.request.AdminRequest;
import com.build.domain.member.admin.dto.response.AdminResponse;
import com.build.domain.member.admin.entity.Admin;
import com.build.domain.member.admin.exception.AdminExistException;
import com.build.domain.member.admin.exception.AdminNotFountException;
import com.build.domain.member.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    @Transactional(readOnly = true)
    public AdminResponse getAdminDetail(AdminRequest request) {
        Admin admin = adminRepository.findByEmail(request.email())
                .orElseThrow(AdminNotFountException::new);

        return AdminResponse.toDto(admin);
    }

    public AdminResponse registerAdmin(AdminRequest request) {
        /* email 검증 우선 실시 */
        if (adminRepository.existsByEmail(request.email())) {
            throw new AdminExistException();
        }

        Admin admin = AdminRequest.toEntity(request);
        adminRepository.save(admin);

        return AdminResponse.toDto(admin);
    }
}
