package com.ws.application.criteria;


import com.ws.domain.model.Product;
import com.ws.domain.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockRatioCriterionTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private Product product;

    @InjectMocks
    private StockRatioCriterion stockRatioCriterion;

    @BeforeEach
    void setUp() {
        Map<String, Integer> stock = Map.of(
                "S", 10,
                "M", 0,
                "L", 5
        );
        when(product.getStock()).thenReturn(stock);
    }

    @Test
    void score_shouldReturnCorrectStockRatio() {
        double score = stockRatioCriterion.score(product);
        assertEquals(2.0 / 3.0, score, 0.001);
    }

    @Test
    void score_shouldReturnZeroWhenNoStockAvailable() {
        Map<String, Integer> emptyStock = Map.of(
                "S", 0,
                "M", 0,
                "L", 0
        );
        when(product.getStock()).thenReturn(emptyStock);
        double score = stockRatioCriterion.score(product);
        assertEquals(0.0, score, 0.001);
    }

    @Test
    void score_shouldReturnZeroWhenNoSizesInStock() {
        Map<String, Integer> emptyStock = Map.of();
        when(product.getStock()).thenReturn(emptyStock);
        double score = stockRatioCriterion.score(product);
        assertEquals(0.0, score, 0.001);
    }
}