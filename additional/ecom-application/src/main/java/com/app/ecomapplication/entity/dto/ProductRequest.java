package com.app.ecomapplication.entity.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private String Category;
    private String imageUrl;
    private Boolean active;
}
