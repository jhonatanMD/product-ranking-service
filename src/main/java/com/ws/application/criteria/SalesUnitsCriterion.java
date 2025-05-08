package com.ws.application.criteria;

import com.ws.domain.model.Product;
import com.ws.domain.repository.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class SalesUnitsCriterion extends ScoringCriterion{

    public SalesUnitsCriterion(ProductRepository productRepository) {
        super(productRepository);
    }

    @Override
    public double score(Product product) {
        return product.getSalesUnits() / (double) getMaxSale();
    }
}
