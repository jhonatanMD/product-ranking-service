package com.ws.application.service;

import com.ws.application.criteria.CriteriaEnum;
import com.ws.application.criteria.SalesUnitsCriterion;
import com.ws.application.criteria.ScoringCriterion;
import com.ws.application.criteria.StockRatioCriterion;
import com.ws.domain.exception.BadRequestException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ScoringCriterionFactory {

    private final ApplicationContext context;

    public ScoringCriterionFactory(ApplicationContext context) {
        this.context = context;
    }

    public ScoringCriterion create(String criterionName) {
        CriteriaEnum criteriaEnum = CriteriaEnum.fromValue(criterionName);
        return switch (criteriaEnum) {
            case CriteriaEnum.SALES_UNITS ->  context.getBean(SalesUnitsCriterion.class);
            case CriteriaEnum.STOCK_RATIO -> context.getBean(StockRatioCriterion.class);
        };
    }
}
