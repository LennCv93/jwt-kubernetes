package com.tecsup.app.micro.order.order_service.infrastructure.web.controller;

import com.tecsup.app.micro.order.order_service.application.usecase.CreateOrderUseCase;
import com.tecsup.app.micro.order.order_service.application.usecase.FindAllOrderUseCase;
import com.tecsup.app.micro.order.order_service.application.usecase.GetByIdOrderUseCase;
import com.tecsup.app.micro.order.order_service.domain.model.Order;
import com.tecsup.app.micro.order.order_service.infrastructure.web.dto.OrderRequest;
import com.tecsup.app.micro.order.order_service.infrastructure.web.dto.OrderResponse;
import com.tecsup.app.micro.order.order_service.infrastructure.web.mapper.OrderResponseMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final FindAllOrderUseCase findAllOrderUseCase;
    private final GetByIdOrderUseCase getByIdOrderUseCase;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        log.info("REST request to get all orders");
        List<Order> orders = findAllOrderUseCase.findAll();

        return ResponseEntity.ok(orders.stream().map(OrderResponseMapper::toResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        log.info("REST request to get order by id: {}", id);

        Order order = getByIdOrderUseCase.execute(id);

        return ResponseEntity.ok(OrderResponseMapper.toResponse(order));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest request) {
        log.info("REST request to create order, with user : {}", request.userId);

        Order order = createOrderUseCase.execute(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(OrderResponseMapper.toResponse(order));
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Order Service running with Clean Architecture!");
    }
}

