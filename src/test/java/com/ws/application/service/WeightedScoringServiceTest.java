package com.ws.application.service;


import com.ws.application.criteria.SalesUnitsCriterion;
import com.ws.application.criteria.StockRatioCriterion;
import com.ws.domain.exception.BadRequestException;
import com.ws.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeightedScoringServiceTest {

    @Mock
    private ScoringCriterionFactory factory;

    @Mock
    private SalesUnitsCriterion salesUnitsCriterion;

    @Mock
    private StockRatioCriterion stockRatioCriterion;

    @Mock
    private Product product;

    private WeightedScoringService weightedScoringService;

    @BeforeEach
    void setUp() {
        weightedScoringService = new WeightedScoringService(factory);
    }

    @Test
    void calculateScore_shouldReturnCorrectScore() {
        Map<String, Double> weights = Map.of(
                "salesUnits", 0.3,
                "stockRatio", 0.7
        );

        when(factory.create("salesUnits")).thenReturn(salesUnitsCriterion);
        when(factory.create("stockRatio")).thenReturn(stockRatioCriterion);
        when(salesUnitsCriterion.score(product)).thenReturn(100.0);
        when(stockRatioCriterion.score(product)).thenReturn(0.8);

        double score = weightedScoringService.calculateScore(product, weights);

        assertEquals(30.0 + 0.56, score, 0.01);
    }

    @Test
    void calculateScore_withUnknownCriterion_shouldThrowBadRequestException() {
        Map<String, Double> weights = Map.of(
                "unknownCriterion", 1.0
        );

        when(factory.create("unknownCriterion")).thenThrow(BadRequestException.class);
        assertThrows(BadRequestException.class, () -> weightedScoringService.calculateScore(product, weights));
    }
}