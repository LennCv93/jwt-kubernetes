package com.tecsup.app.micro.order.order_service.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    public Long id;
    public String orderNumber;
    public Long userId;
    public List<ItemResponse> items;
    public BigDecimal totalAmount;
    public String status;
    public LocalDateTime createdAt;

}
