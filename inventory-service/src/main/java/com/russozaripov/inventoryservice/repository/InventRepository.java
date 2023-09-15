package com.russozaripov.inventoryservice.repository;


import com.russozaripov.inventoryservice.model.InventoryModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventRepository extends MongoRepository<InventoryModel, String> {
}
