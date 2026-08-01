package com.tecsup.app.micro.order.order_service.infrastructure.config;

import com.tecsup.app.micro.order.order_service.application.outbound.ProductClient;
import com.tecsup.app.micro.order.order_service.application.outbound.UserClient;
import com.tecsup.app.micro.order.order_service.application.usecase.CreateOrderUseCase;
import com.tecsup.app.micro.order.order_service.application.usecase.FindAllOrderUseCase;
import com.tecsup.app.micro.order.order_service.application.usecase.GetByIdOrderUseCase;
import com.tecsup.app.micro.order.order_service.domain.repository.OrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CreateOrderUseCase createOrderUseCase (OrderRepository orderRepository, UserClient userClient, ProductClient productClient) {
        return new CreateOrderUseCase(orderRepository, userClient, productClient);
    }

    @Bean
    public GetByIdOrderUseCase getByIdOrderUseCase (OrderRepository orderRepository) {
        return new GetByIdOrderUseCase(orderRepository);
    }

    @Bean
    public FindAllOrderUseCase findAllOrderUseCase (OrderRepository orderRepository) {
        return new FindAllOrderUseCase(orderRepository);
    }
}
