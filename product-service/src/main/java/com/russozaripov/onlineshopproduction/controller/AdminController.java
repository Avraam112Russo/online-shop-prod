package com.russozaripov.onlineshopproduction.controller;

import com.russozaripov.onlineshopproduction.DTO.ProductDTO;
import com.russozaripov.onlineshopproduction.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class AdminController {

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
//    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> add_MetaData_Product(@RequestBody ProductDTO productDTO){
         String result = productService.add_MetaData_Product(productDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/getAllProducts")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductDTO>> get_All_Products(){
        List<ProductDTO> productDTOList =  productService.getAllProducts();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(productDTOList);
    }
    @GetMapping("/getSingleProduct")
    public ResponseEntity<?> get_Single_Product(@RequestParam("id") int id){
        ProductDTO productDTO = productService.getSingleProduct(id);
        if (productDTO != null){
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(productDTO);
        }
        else {
           return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/test")
    public String test(){
        return "work";
    }
}
