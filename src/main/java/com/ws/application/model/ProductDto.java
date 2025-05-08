package com.ws.application.model;

import com.ws.domain.model.Product;

import java.util.Map;

public record ProductDto(
        Integer id,
        String name,
        Integer salesUnits,
        Map<String, Integer> stock
) {
    public static ProductDto fromDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getSalesUnits(),
                product.getStock()
        );
    }
}
