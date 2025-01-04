package com.build.ecommerce.domain.address.repository;

import com.build.ecommerce.domain.address.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressEntityRepository extends JpaRepository<AddressEntity, Long> {
}
