package com.ecommerce.order.models.dtos;

import com.ecommerce.order.models.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent {
    private Long orderId;
    private String userId;
    private OrderStatus orderStatus;
    private BigDecimal totalAmount;
    private List<OrderItemDto> orderItems;
    private LocalDateTime createdAt;


}
