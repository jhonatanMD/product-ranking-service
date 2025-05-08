package com.ws.infrastructure.rest;

import com.ws.application.usecase.RankProductsUseCase;
import com.ws.infrastructure.rest.controller.ProductApi;
import com.ws.infrastructure.rest.dto.ProductScoreResponseDTO;
import com.ws.infrastructure.rest.mapper.ProductResponseMapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ProductController  implements ProductApi {

    private final RankProductsUseCase rankProductsUseCase;
    private final ProductResponseMapper mapper;

    @Override
    public ResponseEntity<List<ProductScoreResponseDTO>> rankProducts(Map<String, Double> requestBody) {
        return ResponseEntity.ok().body(rankProductsUseCase.rankProducts(requestBody).stream().map(mapper::toResponse).toList());
    }
}
