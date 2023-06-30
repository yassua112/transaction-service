package com.transaction.stroreservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transaction.stroreservice.model.entity.ProductItemEntity;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItemEntity, String> {

    Optional<ProductItemEntity> findByProductCode(String productCode);
    
}
