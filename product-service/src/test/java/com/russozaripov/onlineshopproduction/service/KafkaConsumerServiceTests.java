package com.russozaripov.onlineshopproduction.service;

import com.russozaripov.onlineshopproduction.DTO.ProductDTO;
import com.russozaripov.onlineshopproduction.entity.Brand;
import com.russozaripov.onlineshopproduction.entity.Details;
import com.russozaripov.onlineshopproduction.entity.Product;
import com.russozaripov.onlineshopproduction.entity.Type;
import com.russozaripov.onlineshopproduction.event.Supply_product_DTO;
import com.russozaripov.onlineshopproduction.repository.productRepository.ProductRepository;
import com.russozaripov.onlineshopproduction.service.kafkaConsumerService.KafkaConsumerService;
import com.russozaripov.onlineshopproduction.service.productService.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@ExtendWith(MockitoExtension.class)
public class KafkaConsumerServiceTests {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductService productService;
    @InjectMocks
    private KafkaConsumerService kafkaConsumerService;

    @DisplayName("Junit test for get message from inventory service operation.")
    @Test
    public void givenSupplyProductDTO_whenGetMessageFromInventory_thenReturnNothing() throws Exception {
        Supply_product_DTO product_dto = Supply_product_DTO.builder()
                .skuCode("Apple Iphone 15 Pro")
                .quantity(30)
                .build();

        Product product = Product.builder()
                .id(1)
                .skuCode("AppleIphone15Pro")
                .title("Apple Iphone 15 Pro")
                .photoUrl("www.s3service.com/any-photo-url")
                .isInStock(true)
                .type(new Type("MobilePhone"))
                .brand(new Brand("Apple"))
                .details(new Details(165990, "any description"))
                .build();
        BDDMockito.given(productRepository.findProductBySkuCode(ArgumentMatchers.anyString())).willReturn(Optional.of(product));
        BDDMockito.given(productRepository.save(ArgumentMatchers.any(Product.class))).willReturn(product);
        BDDMockito.given(productService.update_Cache_With_AllProducts()).willReturn(ArgumentMatchers.any(CompletableFuture.class));

        kafkaConsumerService.get_Message_From_Inventory(product_dto);

        Mockito.verify(productRepository, Mockito.times(1)).save(product);
        Mockito.verify(productService, Mockito.times(1)).update_Cache_With_AllProducts();
    }
}
