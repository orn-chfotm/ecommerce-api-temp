package com.build.ecommerce.domain.user.entity;

import com.build.ecommerce.core.util.BaseEntity;
import com.build.ecommerce.domain.address.entity.Address;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;

@Entity
@Table(name = "USERS")
@Comment(value = "User Information Table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Comment(value = "user e-mail address, must be unique and not null")
    @Column(unique = true, nullable = false)
    private String email;

    @Comment("user password, must be unique and not null")
    @Column(nullable = false)
    private String password;

    @Comment("user name, must be not null")
    private String name;

    @Comment("user gender, must be not null")
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Comment("user birthday, must be not null")
    @Column(nullable = false)
    private LocalDate birthDate;

    @Comment("user delivery address")
    @OneToOne(mappedBy = "user")
    private Address address;

    @Builder
    public User(String email, String password, String name, Gender gender, LocalDate birthDate) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
    }


}
