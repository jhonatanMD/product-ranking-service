package com.ws.application.service;


import com.ws.application.criteria.SalesUnitsCriterion;
import com.ws.application.criteria.ScoringCriterion;
import com.ws.application.criteria.StockRatioCriterion;
import com.ws.domain.exception.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScoringCriterionFactoryTest {

    @Mock
    private ApplicationContext context;

    @Mock
    private SalesUnitsCriterion salesUnitsCriterion;

    @Mock
    private StockRatioCriterion stockRatioCriterion;

    private ScoringCriterionFactory scoringCriterionFactory;

    @BeforeEach
    void setUp() {
        scoringCriterionFactory = new ScoringCriterionFactory(context);
    }

    @Test
    void createSalesUnitsCriterion_shouldReturnSalesUnitsCriterion() {
        when(context.getBean(SalesUnitsCriterion.class)).thenReturn(salesUnitsCriterion);

        ScoringCriterion criterion = scoringCriterionFactory.create("salesUnits");

        assertNotNull(criterion);
        assertEquals(salesUnitsCriterion, criterion);
    }

    @Test
    void createStockRatioCriterion_shouldReturnStockRatioCriterion() {
        when(context.getBean(StockRatioCriterion.class)).thenReturn(stockRatioCriterion);

        ScoringCriterion criterion = scoringCriterionFactory.create("stockRatio");

        assertNotNull(criterion);
        assertEquals(stockRatioCriterion, criterion);
    }

    @Test
    void createUnknownCriterion_shouldThrowBadRequestException() {
        assertThrows(BadRequestException.class, () -> scoringCriterionFactory.create("unknownCriterion"));
    }
}