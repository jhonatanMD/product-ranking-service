package com.ws.application.usecase.impl;

import com.ws.application.criteria.CriteriaEnum;
import com.ws.application.model.ProductDto;
import com.ws.application.model.ProductScoreDto;
import com.ws.application.service.WeightedScoringService;
import com.ws.application.usecase.RankProductsUseCase;
import com.ws.domain.model.Product;
import com.ws.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static com.ws.application.criteria.CriteriaEnum.isValid;

@Component
@RequiredArgsConstructor
public class RankProductsUseCaseImpl implements RankProductsUseCase {

    private final ProductRepository productRepository;
    private final WeightedScoringService scoringService;

    @Override
    public List<ProductScoreDto> rankProducts(Map<String, Double> criteria) {
        isValid(criteria.keySet());
        List<Product> products = new ArrayList<>(productRepository.findAll());

        return products.stream()
                .map(product -> {
                    var score = scoringService.calculateScore(product, criteria);
                    score = Math.round(score * 100.0) / 100.0;
                    return new ProductScoreDto(ProductDto.fromDto(product), score);
                })
                .sorted(Comparator.comparing(ProductScoreDto::getScore).reversed())
                .toList();
    }
}