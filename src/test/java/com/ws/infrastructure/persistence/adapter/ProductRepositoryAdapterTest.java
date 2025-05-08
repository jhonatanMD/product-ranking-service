package com.ws.infrastructure.persistence.adapter;

import com.ws.domain.model.Product;
import com.ws.infrastructure.persistence.ProductJpaRepository;
import com.ws.infrastructure.persistence.entity.ProductEntity;
import com.ws.infrastructure.persistence.mapper.ProductEntityMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryAdapterTest {

    @Mock
    private ProductJpaRepository productJpaRepository;

    @Mock
    private ProductEntityMapper mapper;

    @InjectMocks
    private ProductRepositoryAdapter adapter;

    @Test
    void findAll_shouldReturnListOfProducts() {
        // Given

        when(mapper.toDomain(any())).thenReturn(mock(Product.class));

        when(productJpaRepository.findAll()).thenReturn(List.of(mock(ProductEntity.class)));

        // When
        List<Product> result = adapter.findAll();

        // Then
        assertEquals(1, result.size());
    }

    @Test
    void getMaxSales_shouldReturnValueFromRepository() {
        // Given
        when(productJpaRepository.findMaxSalesUnits()).thenReturn(200);

        // When
        int maxSales = adapter.getMaxSales();

        // Then
        assertEquals(200, maxSales);
        verify(productJpaRepository).findMaxSalesUnits();
    }
}

