package com.russozaripov.inventoryservice.controller;

import com.russozaripov.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;
    @PostMapping("/")
    public String create_New_Inventory_Model(@RequestBody String skuCode){
        return inventoryService.create_New_Inventory_Model(skuCode);
    }
}
