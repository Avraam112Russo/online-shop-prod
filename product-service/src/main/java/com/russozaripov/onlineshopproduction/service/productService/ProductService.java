package com.russozaripov.onlineshopproduction.service.productService;

import com.russozaripov.onlineshopproduction.DTO.product_to_productDTO.FromProductToProductDTO;
import com.russozaripov.onlineshopproduction.DTO.ProductDTO;
import com.russozaripov.onlineshopproduction.entity.Brand;
import com.russozaripov.onlineshopproduction.entity.Details;
import com.russozaripov.onlineshopproduction.entity.Product;
import com.russozaripov.onlineshopproduction.entity.Type;

import com.russozaripov.onlineshopproduction.exceptionHandler.noSuchProduct.NoSuchBrand.NoSuchBrandException;
import com.russozaripov.onlineshopproduction.exceptionHandler.noSuchProduct.NoSuchProductException;
import com.russozaripov.onlineshopproduction.exceptionHandler.noSuchProduct.noSuchType.NoSuchTypeException;
import com.russozaripov.onlineshopproduction.repository.brandRepository.BrandRepository;
import com.russozaripov.onlineshopproduction.repository.productRepository.ProductRepository;
import com.russozaripov.onlineshopproduction.repository.typeRepository.TypeRepository;
import com.russozaripov.onlineshopproduction.service.productService.filterService.ServiceFilter;
import com.russozaripov.onlineshopproduction.service.updateProductInStock.AllProductsIsInStockService;
import com.russozaripov.onlineshopproduction.service.s3service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final S3Service s3Service;
    private final ProductRepository productRepository;
    private final TypeRepository typeRepository;
    private final BrandRepository brandRepository;
    private final RestTemplate restTemplate;
    private final AllProductsIsInStockService allProductsIsInStockService;
    private final FromProductToProductDTO fromProductToProductDTO;
    private final ServiceFilter serviceFilter;
    private final EntityManager entityManager;


    public String add_New_Product( MultipartFile file) throws IOException {
        String photoUrl = s3Service.add_New_File(file);
        Product product = new Product();
        product.setPhotoUrl(photoUrl);
        productRepository.save(product);
        log.info("Product photo save");
        return String.valueOf(product.getId());
    }

    public String add_MetaData_Product(ProductDTO productDTO) {
                Optional<Type> optionalType = typeRepository.findTypeByName(productDTO.getProductType());
        Type type;
        if (optionalType.isPresent()){
            type = optionalType.get();
        } else {
            type = new Type();
            type.setName(productDTO.getProductType());
        }

        Optional<Brand> optionalBrand = brandRepository.findBrandByName(productDTO.getProductBrand());
        Brand brand;
        if (optionalBrand.isPresent()){
            brand = optionalBrand.get();
        } else {
            brand = new Brand();
            brand.setName(productDTO.getProductBrand());
        }

        Details details = new Details();
        details.setPrice(productDTO.getPrice());
        details.setDescription(productDTO.getDescription());

        Optional<Product> productOptional = productRepository.findById(productDTO.getProductId());
        Product product = null;
        if (productOptional.isPresent()){
            product = productOptional.get();
        }
        product.setTitle(productDTO.getTitle());
        product.setSkuCode(productDTO.getSkuCode());
        product.setType(type);
        product.setBrand(brand);
        product.setDetails(details);

        productRepository.save(product);
        log.info("Product with name: %s successfully update.".formatted(product.getSkuCode()));


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity = new HttpEntity<>(product.getSkuCode(), httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange("http://inventory-service/api/inventory/",
                HttpMethod.POST,
                httpEntity,
                String.class);
        String resultFromInventory =  response.getBody();

        log.info(resultFromInventory);
        return resultFromInventory;
    }

    public ProductDTO get_Single_Product(int id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()){
            return fromProductToProductDTO.productToProductDTO(productOptional.get());
        } else {
            throw new NoSuchProductException("Product with id: %s not found.".formatted(id));
        }
    }
    @PostConstruct
    @Cacheable("allProductsIsInStock")
    @Async
    public CompletableFuture<List<ProductDTO>> get_Products_Is_In_Stock() throws Exception {
       Callable<List<ProductDTO>> listCallable = allProductsIsInStockService.GetAllProductsIsInStockService();
        List<ProductDTO> productDTOList = listCallable.call();
        log.info(productDTOList.toString());
        return CompletableFuture.completedFuture(productDTOList);
    }

    @Async
    @CachePut("allProductsIsInStock")
    public CompletableFuture<List<ProductDTO>> update_Cache_With_AllProducts() throws Exception {
        Callable<List<ProductDTO>> listCallable = allProductsIsInStockService.GetAllProductsIsInStockService();
        List<ProductDTO> productDTOList = listCallable.call();
        log.info(productDTOList.toString());
        return CompletableFuture.completedFuture(productDTOList);
    }

    public String deleteProductById(int id){
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()){
            productRepository.deleteById(id);
            return "Success.";
        } else {
            throw new NoSuchProductException("Product with id: %s not found.".formatted(id));
        }
        }



    public List<ProductDTO> findAllProductsWithSpecification( String type, String brand, Integer minPrice, Integer maxPrice){
        Specification<Product> specification = serviceFilter.filterProductsWithSpecification(type, brand, minPrice, maxPrice);
        List<Product> productList = productRepository.findAll(specification);
        return productList.stream().map(product -> fromProductToProductDTO.productToProductDTO(product)).collect(Collectors.toList());
    }

}

