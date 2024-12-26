package com.build.ecommerce.domain.address.entity;

import com.build.ecommerce.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Comment(value = "Delivery Address Table, Join Users Table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Address {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Long id;

    @Comment(value = "delivery address")
    @Column(nullable = false)
    private String address;

    @Comment(value = "delivery address extra")
    @Column(nullable = false)
    private String extraAddress;

    @Comment(value = "delivery address zipCode")
    @Column(nullable = false)
    private String zipCode;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Builder
    public Address(String address, String extraAddress, String zipCode, User user) {
        this.address = address;
        this.extraAddress = extraAddress;
        this.zipCode = zipCode;
        this.user = user;
    }
}
