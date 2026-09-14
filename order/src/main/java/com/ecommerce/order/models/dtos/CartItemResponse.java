package com.ecommerce.order.models.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemResponse {
    private Long id;
    private String userId;
    private String productId;
    private Integer quantity;
    private BigDecimal price;

}
