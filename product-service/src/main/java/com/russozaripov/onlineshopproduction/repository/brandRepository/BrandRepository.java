package com.russozaripov.onlineshopproduction.repository.brandRepository;

import com.russozaripov.onlineshopproduction.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
    Optional<Brand> findBrandByName(String name);
}
