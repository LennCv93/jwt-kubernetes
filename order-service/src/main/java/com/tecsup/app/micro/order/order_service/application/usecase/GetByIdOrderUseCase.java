package com.tecsup.app.micro.order.order_service.application.usecase;

import com.tecsup.app.micro.order.order_service.domain.model.Order;
import com.tecsup.app.micro.order.order_service.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetByIdOrderUseCase {

    private final OrderRepository orderRepository;

    public Order execute(Long id) {
        return orderRepository.getById(id);
    }
}
