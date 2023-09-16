package com.russozaripov.inventoryservice.service;

import com.russozaripov.inventoryservice.model.InventoryModel;
import com.russozaripov.inventoryservice.repository.InventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class InventoryService {

    @Autowired
    private InventRepository inventRepository;

    public String create_New_Inventory_Model(String skuCode) {
        InventoryModel inventoryModel = InventoryModel.builder()
                .id(UUID.randomUUID().toString())
                .skuCode(skuCode).build();
        inventRepository.save(inventoryModel);
        return "inventoryModel %s successfully insert.".formatted(skuCode);
    }


}
