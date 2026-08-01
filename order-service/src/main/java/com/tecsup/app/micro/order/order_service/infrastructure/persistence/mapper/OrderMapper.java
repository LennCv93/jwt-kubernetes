package com.tecsup.app.micro.order.order_service.infrastructure.persistence.mapper;

import com.tecsup.app.micro.order.order_service.domain.model.Item;
import com.tecsup.app.micro.order.order_service.domain.model.Order;
import com.tecsup.app.micro.order.order_service.domain.model.Product;
import com.tecsup.app.micro.order.order_service.infrastructure.persistence.entity.OrderEntity;
import com.tecsup.app.micro.order.order_service.infrastructure.persistence.entity.OrderItemEntity;

import java.util.List;

public class OrderMapper {

    public static OrderEntity toEntity(Order order) {
        OrderEntity orderEntity = OrderEntity.builder()
                .orderNumber(order.orderNumber)
                .userId(order.userId)
                .status(order.status)
                .totalAmount(order.totalAmount)
                .createdAt(order.createdAt)
                .updatedAt(order.updatedAt)
                .build();

        List<OrderItemEntity> itemsEntity = order.items.stream()
                .map(item ->
                                OrderItemEntity.builder()
                                        .productId(item.product.id)
                                        .quantity(item.quantity)
                                        .unitPrice(item.unitPrice).subtotal(item.subtotal).order(orderEntity).build()
                        ).toList();

        orderEntity.setItems(itemsEntity);

        return orderEntity;

    }

    public static Order toDomain(OrderEntity entity) {
        Order domain = Order.builder()
                .id(entity.getId())
                .orderNumber(entity.getOrderNumber())
                .userId(entity.getUserId())
                .totalAmount(entity.getTotalAmount())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();

        List<Item> itemList = entity.getItems().stream()
                .map(item ->
                        Item.builder()
                                .id(item.getId())
                                .product(Product.builder().id(item.getProductId()).build())
                                .quantity(item.getQuantity())
                                .unitPrice(item.getUnitPrice())
                                .subtotal(item.getSubtotal())
                                .build()
                        ).toList();

        domain.setItems(itemList);

        return domain;
    }
}
