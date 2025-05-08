package com.ws.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    private Long id;

    private String name;

    @Column(name = "sales_units")
    private Integer salesUnits;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StockEntity> stock;
}
