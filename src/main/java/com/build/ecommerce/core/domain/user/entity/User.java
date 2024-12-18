package com.build.ecommerce.core.domain.user.entity;

import com.build.ecommerce.core.domain.address.entity.Address;
import com.build.ecommerce.util.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USERS")
@Comment("User Information Table")
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
    private String birthday;

    @Comment("user delivery address")
    @OneToOne(mappedBy = "user")
    private Address address;
}
