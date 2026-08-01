package com.tecsup.app.micro.order.order_service.infrastructure.persistence.repository;

import com.tecsup.app.micro.order.order_service.domain.model.Order;
import com.tecsup.app.micro.order.order_service.domain.repository.OrderRepository;
import com.tecsup.app.micro.order.order_service.infrastructure.persistence.entity.OrderEntity;
import com.tecsup.app.micro.order.order_service.infrastructure.persistence.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Order save(Order order) {

        OrderEntity entitySaved = orderJpaRepository.save(OrderMapper.toEntity(order));

        return OrderMapper.toDomain(entitySaved);
    }

    @Override
    public Order getById(Long id) {
        Optional<OrderEntity> entity = orderJpaRepository.findById(id);

        if (entity.isEmpty()) throw new IllegalArgumentException("Order " + id + "not found");

        return OrderMapper.toDomain(entity.get());
    }

    @Override
    public List<Order> findAll() {
        return orderJpaRepository.findAll().stream()
                .map(OrderMapper::toDomain).toList();
    }
}
