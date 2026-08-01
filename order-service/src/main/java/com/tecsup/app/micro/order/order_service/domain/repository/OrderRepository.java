package com.tecsup.app.micro.order.order_service.domain.repository;

import com.tecsup.app.micro.order.order_service.domain.model.Order;

import java.util.List;

public interface OrderRepository {

    Order save(Order order);

    Order getById(Long id);

    List<Order> findAll();
}
