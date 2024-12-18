package com.build.ecommerce.core.domain.address.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
@Entity
public class Address {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESSS_ID")
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
}
