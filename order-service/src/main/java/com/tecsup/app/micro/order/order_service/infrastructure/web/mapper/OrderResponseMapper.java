package com.tecsup.app.micro.order.order_service.infrastructure.web.mapper;

import com.tecsup.app.micro.order.order_service.domain.model.Order;
import com.tecsup.app.micro.order.order_service.infrastructure.web.dto.ItemResponse;
import com.tecsup.app.micro.order.order_service.infrastructure.web.dto.OrderResponse;
import com.tecsup.app.micro.order.order_service.infrastructure.web.dto.ProductResponse;

import java.util.List;

public class OrderResponseMapper {

    public static OrderResponse toResponse(Order domain) {
        OrderResponse response = OrderResponse.builder()
                .id(domain.getId())
                .orderNumber(domain.getOrderNumber())
                .userId(domain.getUserId())
                .totalAmount(domain.getTotalAmount())
                .status(domain.getStatus())
                .createdAt(domain.getCreatedAt())
                .build();

        List<ItemResponse> itemList = domain.getItems().stream()
                .map(item ->
                        ItemResponse.builder()
                                .id(item.getId())
                                .product(ProductResponse.builder().id(item.product.id).name(item.product.name).price(item.product.price).build())
                                .quantity(item.getQuantity())
                                .unitPrice(item.getUnitPrice())
                                .subtotal(item.getSubtotal())
                                .build()
                ).toList();

        response.setItems(itemList);

        return response;
    }
}
