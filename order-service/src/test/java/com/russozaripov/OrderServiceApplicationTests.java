package com.russozaripov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class OrderServiceApplicationTests {
    @Test
    void methodTest(){
        Assertions.assertTrue(true);
    }
//    @InjectMocks
//    private AllProductsIsInStockService productService;
//
//    @Mock
//    private ProductRepository productRepository;
//    @Mock
//    private FromProductToProductDTO fromProductToProductDTO;
//    @Test
//    void getAllProductsIsInStockTest() throws Exception {
//
////        List<Product> productListMock = new ArrayList<>();
////        productListMock.add(new Product(1, "AppleIphone13", "Apple Iphone 13", "www.some-url.com",
////                true,
////                new Type(1, "MobilePhone"),
////                new Brand(1, "Apple"),
////                new Details(1, 79990, "some desc...")));
////        productListMock.add(new Product(2, "SamsungGalaxyS22", "Samsung Galaxy S22", "www.some-url.com",
////                false,
////                new Type(1, "MobilePhone"),
////                new Brand(2, "Samsung"),
////                new Details(2, 49990, "some desc...")));
////        productListMock.add(new Product(3, "HuaweiPro30C", "Huawei Pro 30C", "www.some-url.com",
////                true,
////                new Type(1, "MobilePhone"),
////                new Brand(3, "Huawei"),
////                new Details(3, 65990, "some desc...")));
////        Mockito.when(productRepository.findAll()).thenReturn(productListMock);
////        Callable<List<ProductDTO>> result = productService.GetAllProductsIsInStockService();
////        List<ProductDTO> productsOnlyInStock = result.call();
////        Assertions.assertEquals(0, productsOnlyInStock.size());
//
//        List<Product> productListMock = new ArrayList<>();
//        productListMock.add(new Product(1, "AppleIphone13", "Apple Iphone 13", "www.some-url.com",
//                true,
//                new Type(1, "MobilePhone"),
//                new Brand(1, "Apple"),
//                new Details(1, 79990, "some desc...")));
//        productListMock.add(new Product(2, "SamsungGalaxyS22", "Samsung Galaxy S22", "www.some-url.com",
//                false,
//                new Type(1, "MobilePhone"),
//                new Brand(2, "Samsung"),
//                new Details(2, 49990, "some desc...")));
//        productListMock.add(new Product(3, "HuaweiPro30C", "Huawei Pro 30C", "www.some-url.com",
//                true,
//                new Type(1, "MobilePhone"),
//                new Brand(3, "Huawei"),
//                new Details(3, 65990, "some desc...")));
//
//        Mockito.when(productRepository.findAll()).thenReturn(productListMock);
//
//
////        Mockito.when(fromProductToProductDTO.productToProductDTO(Mockito.any(Product.class)))
////                .thenAnswer( invocation -> { //
////                    Object[] arguments =  invocation.getArguments();
////                    ProductDTO productDTO = null;
////                    if (arguments.length > 0 && arguments[0] instanceof Product){
////
////                    Product product = (Product) arguments[0];
////                        productDTO = ProductDTO.builder()
////                                .productId(product.getId())
////                                .isInStock(product.isInStock())
////                                .skuCode(product.getSkuCode())
////                                .title(product.getTitle())
////                                .productType(product.getType().getName())
////                                .productBrand(product.getBrand().getName())
////                                .price(product.getDetails().getPrice())
////                                .description(product.getDetails().getDescription())
////                                .localTime(LocalTime.now())
////                                .build();
////                    }
////                        return productDTO;
////
////                });
//        for (Product product : productListMock){
//            Mockito.when(fromProductToProductDTO.productToProductDTO(product)).thenReturn(
//                    ProductDTO.builder()
//                                .productId(product.getId())
//                                .isInStock(product.isInStock())
//                                .skuCode(product.getSkuCode())
//                                .title(product.getTitle())
//                                .productType(product.getType().getName())
//                                .productBrand(product.getBrand().getName())
//                                .price(product.getDetails().getPrice())
//                                .description(product.getDetails().getDescription())
//                                .localTime(LocalTime.now())
//                                .build()
//            );
//        }
//
//        Callable<List<ProductDTO>> result = productService.GetAllProductsIsInStockService();
//        List<ProductDTO> productsOnlyInStock = result.call();
//
//        // Проверяем, что результат содержит только продукты, у которых isInStock == true
//        Assertions.assertEquals(2, productsOnlyInStock.size());
//    }

}
