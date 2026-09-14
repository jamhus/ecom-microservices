package com.app.ecomapplication.models.dtos;

import com.app.ecomapplication.models.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemResponse {
    private Long id;
    private UserResponse user;
    private Product product;
    private Integer quantity;
    private BigDecimal price;

}
