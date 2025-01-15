package com.build.ecommerce.domain.admin.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Comment(value = "Admin infomation", on = "TABLE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Admin {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADMIN_ID")
    private Long id;

    @Comment("관리자 ID, unique and not null")
    @Column(unique = true, nullable = false)
    private String email;

    @Comment("관리자 PW, not null")
    @Column(nullable = false)
    private String password;

    @Comment("관리자 이름, not null")
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private AdminRole role;

    @Builder
    public Admin(String email, String password, String name, AdminRole role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }
}

