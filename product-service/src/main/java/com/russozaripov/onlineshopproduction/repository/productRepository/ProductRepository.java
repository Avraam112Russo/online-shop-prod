package com.russozaripov.onlineshopproduction.repository.productRepository;

import com.russozaripov.onlineshopproduction.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
