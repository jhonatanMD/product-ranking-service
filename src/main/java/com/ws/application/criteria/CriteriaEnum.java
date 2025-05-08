package com.ws.application.criteria;

import com.ws.domain.exception.BadRequestException;
import lombok.Getter;

import java.util.Arrays;
import java.util.Set;

@Getter
public enum CriteriaEnum {

    SALES_UNITS("salesUnits"),
    STOCK_RATIO("stockRatio");

    private final String criteria;

    CriteriaEnum(String criteria) {
        this.criteria = criteria;
    }

    public static void isValid(Set<String> criterion) {
        var isValid = criterion.stream()
                .allMatch(c -> Arrays.stream(values())
                        .anyMatch(v -> v.getCriteria().equalsIgnoreCase(c)));
        if(!isValid){
            throw new BadRequestException("Invalid criteria: " + criterion);
        }
    }

    public static CriteriaEnum fromValue(String value) {
        for (CriteriaEnum criteria : values()) {
            if (criteria.getCriteria().equalsIgnoreCase(value)) {
                return criteria;
            }
        }
        throw new BadRequestException("Invalid value: " + value);
    }
}
