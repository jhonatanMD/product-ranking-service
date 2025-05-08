package com.ws.application.criteria;

import com.ws.domain.model.Product;
import com.ws.domain.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StockRatioCriterion extends ScoringCriterion{

    public StockRatioCriterion(ProductRepository productRepository) {
        super(productRepository);
    }

    @Override
    public double score(Product product) {
        Map<String, Integer> stock = product.getStock();
        int totalSizes = stock.size();
        long availableSizes = stock.values().stream().filter(q -> q > 0).count();
        return totalSizes > 0 ? (double) availableSizes / totalSizes : 0.0;
    }
}
