package com.ws.infrastructure.persistence.mapper;

import com.ws.domain.model.Product;
import com.ws.infrastructure.persistence.entity.ProductEntity;
import com.ws.infrastructure.persistence.entity.StockEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface ProductEntityMapper {

    @Mapping(source = "stock", target = "stock")
    Product toDomain(ProductEntity entity);

    default Map<String, Integer> map(List<StockEntity> stockEntities) {
        if (stockEntities == null) {
            return Collections.emptyMap();
        }
        return stockEntities.stream()
                .collect(Collectors.toMap(StockEntity::getSize, StockEntity::getQuantity));
    }
}
