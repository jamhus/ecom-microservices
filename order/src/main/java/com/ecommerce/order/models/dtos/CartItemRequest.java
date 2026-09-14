package com.ecommerce.order.models.dtos;

import lombok.Data;

@Data
public class CartItemRequest {
    private String productId;
    private Integer quantity;
}
