package com.ws.application.criteria;

import com.ws.domain.model.Product;
import com.ws.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public abstract class ScoringCriterion {

  private final ProductRepository productRepository;

   public abstract double score(Product product);
   public int getMaxSale(){
       return productRepository.getMaxSales();
   }
}