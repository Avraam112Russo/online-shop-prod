package com.russozaripov.onlineshopproduction.controller;

import com.russozaripov.onlineshopproduction.DTO.ProductDTO;
import com.russozaripov.onlineshopproduction.exceptionHandler.ResourceNotFoundException;
import com.russozaripov.onlineshopproduction.service.productService.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Path;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/product")
@Slf4j
public class AdminController {

    @Value("${eureka.instance.instance-id}")
    private String PRODUCT_SERVICE_INSTANCE_ID;
    @Autowired
    private ProductService productService;
    @PostMapping("/addProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> Add_New_Product(@RequestParam("file")MultipartFile file) throws IOException {
                String result = productService.add_New_Product(file);
                return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    @PutMapping("/addProduct/metaData")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> add_MetaData_Product(@RequestBody ProductDTO productDTO){
         String result =  productService.add_MetaData_Product(productDTO);
         return new ResponseEntity<>(result, HttpStatus.OK);
        // HttpStatus.OK идет в заголовок и ResponseEntity будет представлен только в виде строки Result
    }

    @GetMapping("/getAllProducts")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductDTO>> get_All_Products() throws Exception {
//        List<ProductDTO> productDTOList =  productService.getAllProducts();
        CompletableFuture<List<ProductDTO>> productDTOList = productService.get_Products_Is_In_Stock();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(productDTOList.get());
    }
    @GetMapping("/getSingleProduct/{productID}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> get_Single_Product(@PathVariable("productID") int id){
        ProductDTO productDTO = productService.get_Single_Product(id);
        if (productDTO != null){
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(productDTO);
        }
        else {
           return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/deleteProduct/{productID}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteProductById(@PathVariable("productID")int id){
        if (productService.deleteProductById(id).equals( "success")){
        return ResponseEntity.ok("Product successfully delete.");
        }
        return ResponseEntity.notFound().build();
    }
}
