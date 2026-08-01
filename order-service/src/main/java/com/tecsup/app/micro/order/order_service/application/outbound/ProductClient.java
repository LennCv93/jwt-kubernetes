package com.tecsup.app.micro.order.order_service.application.outbound;

import com.tecsup.app.micro.order.order_service.infrastructure.client.product.ProductClientResponse;

public interface ProductClient {

    ProductClientResponse getProductById(Long id);
}
