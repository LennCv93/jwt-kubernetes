package com.tecsup.app.micro.order.order_service.application.usecase;

import com.tecsup.app.micro.order.order_service.application.outbound.ProductClient;
import com.tecsup.app.micro.order.order_service.application.outbound.UserClient;
import com.tecsup.app.micro.order.order_service.domain.model.Item;
import com.tecsup.app.micro.order.order_service.domain.model.Order;
import com.tecsup.app.micro.order.order_service.domain.model.Product;
import com.tecsup.app.micro.order.order_service.domain.repository.OrderRepository;
import com.tecsup.app.micro.order.order_service.infrastructure.client.product.ProductClientResponse;
import com.tecsup.app.micro.order.order_service.infrastructure.client.user.UserClientResponse;
import com.tecsup.app.micro.order.order_service.infrastructure.web.dto.OrderRequest;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final UserClient userClient;
    private final ProductClient productClient;

    public Order execute(OrderRequest request) {

        UserClientResponse userClientResponse = userClient.getUserById(request.userId);

        List<Item> items = request.items.stream().map(product -> {
            ProductClientResponse productClientResponse = productClient.getProductById(product.productId);

            Product productResponse = Product.builder().id(productClientResponse.getId()).name(productClientResponse.getName()).price(productClientResponse.getPrice()).build();

            return Item.builder().product(productResponse).quantity(product.quantity).unitPrice(productResponse.price).subtotal(productResponse.price.multiply(BigDecimal.valueOf(product.quantity))).build();
        }
        ).toList();

        BigDecimal totalAmount = items.stream().map(item -> item.subtotal).reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = Order.builder()
                .orderNumber(generateUniqueString())
                .userId(userClientResponse.getId())
                .items(items)
                .totalAmount(totalAmount)
                .status("CONFIRMED").build();

        Order orderResponse = orderRepository.save(order);

        orderResponse.items.forEach(item -> {
            items.stream()
                    .filter(i -> i.product.id.equals(item.product.id))
                    .findFirst()
                    .ifPresent(i -> {
                        item.product.setName(i.product.name);
                        item.product.setPrice(i.product.price);
                    });
        });
        return orderResponse;
    }

    private String generateUniqueString() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
