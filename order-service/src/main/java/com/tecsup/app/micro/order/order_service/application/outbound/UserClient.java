package com.tecsup.app.micro.order.order_service.application.outbound;

import com.tecsup.app.micro.order.order_service.infrastructure.client.user.UserClientResponse;

public interface UserClient {

    UserClientResponse getUserById(Long id);
}
