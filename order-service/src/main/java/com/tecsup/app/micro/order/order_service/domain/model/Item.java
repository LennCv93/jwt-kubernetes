package com.tecsup.app.micro.order.order_service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    public Long id;
    public Product product;
    public int quantity;
    public BigDecimal unitPrice;
    public BigDecimal subtotal;
}
