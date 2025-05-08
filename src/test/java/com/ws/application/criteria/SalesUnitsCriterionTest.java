package com.ws.application.criteria;

import com.ws.domain.model.Product;
import com.ws.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SalesUnitsCriterionTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private Product product;

    @InjectMocks
    private SalesUnitsCriterion salesUnitsCriterion;

    @Test
    void score_shouldReturnCorrectScore() {
        when(product.getSalesUnits()).thenReturn(100);
        when(productRepository.getMaxSales()).thenReturn(200);

        double result = salesUnitsCriterion.score(product);

        assertEquals(0.5, result, 0.001);
    }
}
