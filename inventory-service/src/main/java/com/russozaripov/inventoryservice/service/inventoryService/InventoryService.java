package com.russozaripov.inventoryservice.service.inventoryService;

import com.russozaripov.inventoryservice.DTO.Supply_product_DTO;
import com.russozaripov.inventoryservice.model.InventoryModel;
import com.russozaripov.inventoryservice.repository.InventRepository;
import com.russozaripov.inventoryservice.service.kafkaBroker.KafkaProducerService;
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
    @Autowired
    private KafkaProducerService kafkaProducerService;

    public String create_New_Inventory_Model(String skuCode) {
        InventoryModel inventoryModel = InventoryModel.builder()
                .id(UUID.randomUUID().toString())
                .skuCode(skuCode).build();
        inventRepository.save(inventoryModel);
        return "inventoryModel %s successfully insert.".formatted(skuCode);
    }

    public String supply_Product(Supply_product_DTO supply_product_dto){
        InventoryModel inventoryModel = inventRepository.findInventoryModelBySkuCode(supply_product_dto.getSkuCode()).get();
        inventoryModel.setQuantity(supply_product_dto.getQuantity());
        inventRepository.save(inventoryModel);
        kafkaProducerService.sendMessageToKafka(supply_product_dto.getSkuCode());
        return "inventory-model: %s successfully update.".formatted(supply_product_dto.getSkuCode());
    }


}