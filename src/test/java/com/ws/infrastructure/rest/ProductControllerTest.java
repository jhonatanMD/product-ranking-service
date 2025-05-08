package com.ws.infrastructure.rest;


import com.ws.application.model.ProductScoreDto;
import com.ws.application.usecase.RankProductsUseCase;
import com.ws.infrastructure.rest.dto.ProductScoreResponseDTO;
import com.ws.infrastructure.rest.mapper.ProductResponseMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private RankProductsUseCase rankProductsUseCase;

    @Mock
    private ProductResponseMapper mapper;

    @InjectMocks
    private ProductController productController;

    @Test
    void rankProducts_shouldReturnRankedList() {

        Map<String, Double> request = Map.of("salesWeight", 0.3, "stockWeight", 0.7);
        ProductScoreResponseDTO response = mock(ProductScoreResponseDTO.class);

        List<ProductScoreDto> products = List.of(new ProductScoreDto());

        when(rankProductsUseCase.rankProducts(any())).thenReturn(products);
        when(mapper.toResponse(any())).thenReturn(response);

        List<ProductScoreResponseDTO> result = productController.rankProducts(request).getBody();

        assertNotNull(result);
        assertEquals(1, result.size());
    }
}

