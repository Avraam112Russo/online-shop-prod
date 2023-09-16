package com.russozaripov.inventoryservice.controller;

import com.russozaripov.inventoryservice.DTO.Supply_product_DTO;
import com.russozaripov.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;
    @PostMapping("/")
    public ResponseEntity<String> create_New_Inventory_Model(@RequestBody String skuCode){
               String result =  inventoryService.create_New_Inventory_Model(skuCode);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }
//    @PostMapping("/supply")
//    public ResponseEntity<String> supply_New_Product(@RequestBody Supply_product_DTO product_dto){
//        String result = inventoryService.supplyProduct(product_dto);
//        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
//    }
}
