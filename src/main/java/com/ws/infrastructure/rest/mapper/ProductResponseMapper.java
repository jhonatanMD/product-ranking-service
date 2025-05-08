package com.ws.infrastructure.rest.mapper;

import com.ws.application.model.ProductScoreDto;
import com.ws.infrastructure.rest.dto.ProductScoreResponseDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ProductResponseMapper {
    ProductScoreResponseDTO toResponse(ProductScoreDto productDto);
}
