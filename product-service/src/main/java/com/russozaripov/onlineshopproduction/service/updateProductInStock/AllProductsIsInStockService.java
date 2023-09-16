package com.russozaripov.onlineshopproduction.service.updateProductInStock;

import com.russozaripov.onlineshopproduction.DTO.ProductDTO;
import com.russozaripov.onlineshopproduction.DTO.product_to_productDTO.FromProductToProductDTO;
import com.russozaripov.onlineshopproduction.entity.Product;
import com.russozaripov.onlineshopproduction.repository.productRepository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AllProductsIsInStockService {

    @Autowired
    private ProductRepository productRepository;

    public Callable<List<ProductDTO>> GetAllProductsIsInStockService() {
        return () -> {
        List<Product> productsList = productRepository.findAll();
        log.info("ThreadPoolProduct start work");
        Iterator<Product> productIterator = productsList.iterator();
        List<Product> products_Is_In_Stock = new ArrayList<>();
        while (productIterator.hasNext()){
            Product product = productIterator.next();
            if (product.isInStock()){
                products_Is_In_Stock.add(product);
            }
        }
        List<ProductDTO>productDTOList = products_Is_In_Stock.stream().map(product ->
                FromProductToProductDTO.fromProductToProductDTO(product)
        ).collect(Collectors.toList());
        return productDTOList;
        };
    }
}
