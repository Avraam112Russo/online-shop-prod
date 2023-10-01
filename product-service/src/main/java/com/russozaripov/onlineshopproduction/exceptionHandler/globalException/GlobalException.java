package com.russozaripov.onlineshopproduction.exceptionHandler.globalException;

import com.russozaripov.onlineshopproduction.exceptionHandler.noSuchProduct.NoSuchBrand.BrandIncorrectData;
import com.russozaripov.onlineshopproduction.exceptionHandler.noSuchProduct.NoSuchBrand.NoSuchBrandException;
import com.russozaripov.onlineshopproduction.exceptionHandler.noSuchProduct.NoSuchProductException;
import com.russozaripov.onlineshopproduction.exceptionHandler.noSuchProduct.ProductIncorrectData;
import com.russozaripov.onlineshopproduction.exceptionHandler.noSuchProduct.noSuchType.NoSuchTypeException;
import com.russozaripov.onlineshopproduction.exceptionHandler.noSuchProduct.noSuchType.TypeIncorrectData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler
    public ResponseEntity<ProductIncorrectData> noProductException(NoSuchProductException noSuchProductException){
        ProductIncorrectData productIncorrectData = new ProductIncorrectData();
        productIncorrectData.setInfo(noSuchProductException.getMessage());
        return new ResponseEntity<>(productIncorrectData, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<BrandIncorrectData> noProductException(NoSuchBrandException noSuchBrandException){
        BrandIncorrectData brandIncorrectData = new BrandIncorrectData();
        brandIncorrectData.setInfo(noSuchBrandException.getMessage());
        return new ResponseEntity<>(brandIncorrectData, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<TypeIncorrectData> noProductException(NoSuchTypeException noSuchTypeException){
        TypeIncorrectData typeIncorrectData = new TypeIncorrectData();
        typeIncorrectData.setInfo(noSuchTypeException.getMessage());
        return new ResponseEntity<>(typeIncorrectData, HttpStatus.NOT_FOUND);
    }
}
