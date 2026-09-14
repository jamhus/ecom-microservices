package com.ecommerce.product.models.dtos;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private String Category;
    private String imageUrl;
    private Boolean active;
}
