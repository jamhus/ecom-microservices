package com.app.ecomapplication.entity.dto;

import com.app.ecomapplication.entity.Product;
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
