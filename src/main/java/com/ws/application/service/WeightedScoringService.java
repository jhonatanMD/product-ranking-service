package com.ws.application.service;

import com.ws.application.criteria.ScoringCriterion;
import com.ws.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class WeightedScoringService {

    private final ScoringCriterionFactory factory;

    public double calculateScore(Product product, final Map<String, Double> weights) {
        double totalScore = 0.0;

        for (Map.Entry<String, Double> entry : weights.entrySet()) {
            ScoringCriterion criterion = factory.create(entry.getKey());
            double criterionScore = criterion.score(product);
            totalScore += criterionScore * entry.getValue();
        }
        return totalScore;
    }
}