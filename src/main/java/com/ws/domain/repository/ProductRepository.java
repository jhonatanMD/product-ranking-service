package com.ws.domain.repository;

import com.ws.domain.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    int getMaxSales();
}
