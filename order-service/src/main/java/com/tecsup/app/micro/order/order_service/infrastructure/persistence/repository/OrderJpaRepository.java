package com.tecsup.app.micro.order.order_service.infrastructure.persistence.repository;

import com.tecsup.app.micro.order.order_service.infrastructure.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {
}
