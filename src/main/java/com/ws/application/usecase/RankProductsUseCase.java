package com.ws.application.usecase;

import com.ws.application.model.ProductScoreDto;

import java.util.List;
import java.util.Map;

public interface RankProductsUseCase {

    List<ProductScoreDto> rankProducts(Map<String, Double> criteria);
}
