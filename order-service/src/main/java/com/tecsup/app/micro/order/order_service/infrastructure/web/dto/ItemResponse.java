package com.tecsup.app.micro.order.order_service.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {
    public Long id;
    public ProductResponse product;
    public int quantity;
    public BigDecimal unitPrice;
    public BigDecimal subtotal;
}
