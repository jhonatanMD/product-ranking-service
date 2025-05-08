package com.ws.application.usecase.impl;


import com.ws.application.model.ProductScoreDto;
import com.ws.application.service.WeightedScoringService;
import com.ws.domain.model.Product;
import com.ws.domain.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RankProductsUseCaseImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private WeightedScoringService scoringService;

    @Mock
    private Product product;


    private RankProductsUseCaseImpl rankProductsUseCaseImpl;

    @BeforeEach
    void setUp() {
        rankProductsUseCaseImpl = new RankProductsUseCaseImpl(productRepository, scoringService);
    }

    @Test
    void rankProducts_shouldReturnRankedList() {
        Map<String, Double> criteria = Map.of(
                "salesUnits", 0.3,
                "stockRatio", 0.7
        );

        when(productRepository.findAll()).thenReturn(List.of(product));
        when(scoringService.calculateScore(product, criteria)).thenReturn(100.0);

        List<ProductScoreDto> rankedProducts = rankProductsUseCaseImpl.rankProducts(criteria);

        assertNotNull(rankedProducts);
        assertEquals(1, rankedProducts.size());
        assertEquals(100.0, rankedProducts.get(0).getScore());
    }

    @Test
    void rankProducts_shouldSortByScore() {
        Map<String, Double> criteria = Map.of(
                "salesUnits", 0.3,
                "stockRatio", 0.7
        );

        Product product2 = mock(Product.class);
        when(productRepository.findAll()).thenReturn(List.of(product, product2));
        when(scoringService.calculateScore(product, criteria)).thenReturn(100.0);
        when(scoringService.calculateScore(product2, criteria)).thenReturn(150.0);

        List<ProductScoreDto> rankedProducts = rankProductsUseCaseImpl.rankProducts(criteria);

        assertNotNull(rankedProducts);
        assertEquals(2, rankedProducts.size());
        assertTrue(rankedProducts.get(0).getScore() > rankedProducts.get(1).getScore());
    }

    @Test
    void rankProducts_withEmptyList_shouldReturnEmptyList() {
        Map<String, Double> criteria = Map.of(
                "salesUnits", 0.3,
                "stockRatio", 0.7
        );

        when(productRepository.findAll()).thenReturn(Collections.emptyList());

        List<ProductScoreDto> rankedProducts = rankProductsUseCaseImpl.rankProducts(criteria);

        assertNotNull(rankedProducts);
        assertTrue(rankedProducts.isEmpty());
    }
}