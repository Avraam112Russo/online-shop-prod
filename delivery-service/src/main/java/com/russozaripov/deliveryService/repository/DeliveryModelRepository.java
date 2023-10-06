package com.russozaripov.deliveryService.repository;

import com.russozaripov.deliveryService.model.DeliveryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryModelRepository extends JpaRepository<DeliveryModel, Integer> {
}
