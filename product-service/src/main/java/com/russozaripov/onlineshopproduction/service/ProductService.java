package com.russozaripov.onlineshopproduction.service;

import com.russozaripov.onlineshopproduction.DTO.product_to_productDTO.FromProductToProductDTO;
import com.russozaripov.onlineshopproduction.DTO.ProductDTO;
import com.russozaripov.onlineshopproduction.entity.Brand;
import com.russozaripov.onlineshopproduction.entity.Details;
import com.russozaripov.onlineshopproduction.entity.Product;
import com.russozaripov.onlineshopproduction.entity.Type;

import com.russozaripov.onlineshopproduction.repository.brandRepository.BrandRepository;
import com.russozaripov.onlineshopproduction.repository.productRepository.ProductRepository;
import com.russozaripov.onlineshopproduction.repository.typeRepository.TypeRepository;
import com.russozaripov.onlineshopproduction.service.s3service.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private S3Service s3Service;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private RestTemplate restTemplate;




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
        product.setSkuCode(productDTO.getSkuCode());
        product.setType(type);
        product.setBrand(brand);
        product.setDetails(details);

        productRepository.save(product);
        log.info("Product with name: %s successfully update.".formatted(product.getSkuCode()));

////        Map<String, String> map = Map.of("skuCode", product.getSkuCode());

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

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> FromProductToProductDTO.fromProductToProductDTO(product)
                ).collect(Collectors.toList());

    }

    public ProductDTO getSingleProduct(int id) {
        Optional<Product> optional = productRepository.findById(id);
        Product product = null;
        if (optional.isPresent()){
            product = optional.get();
        }
        return FromProductToProductDTO.fromProductToProductDTO(product);
    }

}

