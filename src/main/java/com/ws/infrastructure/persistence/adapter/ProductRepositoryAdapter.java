package com.ws.infrastructure.persistence.adapter;

import com.ws.domain.model.Product;
import com.ws.domain.repository.ProductRepository;
import com.ws.infrastructure.persistence.ProductJpaRepository;
import com.ws.infrastructure.persistence.mapper.ProductEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;
    private final ProductEntityMapper mapper;

    @Override
    public List<Product> findAll() {
        return productJpaRepository.findAll()
                .stream().map(mapper::toDomain)
                .toList();
    }

    @Override
    @Cacheable("maxSales")
    public int getMaxSales() {
        log.info("Calculating max sales...");
        return productJpaRepository.findMaxSalesUnits();
    }
}
