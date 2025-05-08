package com.ws.infrastructure.persistence;

import com.ws.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT MAX(p.salesUnits) FROM ProductEntity p")
    int findMaxSalesUnits();
}
