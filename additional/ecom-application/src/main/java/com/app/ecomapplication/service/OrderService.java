package com.app.ecomapplication.service;

import com.app.ecomapplication.entity.*;
import com.app.ecomapplication.entity.dto.OrderItemDto;
import com.app.ecomapplication.entity.dto.OrderResponse;
import com.app.ecomapplication.repository.OrderRepository;
import com.app.ecomapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartService cartService;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    public Optional<OrderResponse> createOrder(String userId) {
        // Validate for cart items
        List<CartItem> cartItems = cartService.fetchCartItems(userId);
        if (cartItems.isEmpty()) {
            return Optional.empty();
        }

        // validate for user
        var userOpt = userRepository.findById(Long.valueOf(userId));
        if (userOpt.isEmpty()) {
            return Optional.empty();
        }
        var user = userOpt.get();

        // calculate total price
        BigDecimal totalPrice = cartItems.stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // create order
        var order = new Order();
        order.setUser(user);
        order.setTotalAmount(totalPrice);
        order.setStatus(OrderStatus.CONFIRMED);

        List<OrderItem> orderItems = cartItems.stream()
                .map(item -> new OrderItem(null, item.getProduct(), item.getQuantity(), item.getPrice(), order))
                .toList();

        order.setOrderItems(orderItems);
        var savedOrder = orderRepository.save(order);

        // clear cart items
        cartService.clearCart(userId);
        
        return Optional.of(mapToOrderResponse(savedOrder));
    }

    private OrderResponse mapToOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getOrderItems().stream()
                        .map(orderItem -> new OrderItemDto(
                                orderItem.getId(),
                                orderItem.getProduct().getId(),
                                orderItem.getQuantity(),
                                orderItem.getProduct().getPrice(),
                                orderItem.getProduct().getPrice().multiply(new BigDecimal(orderItem.getQuantity()))
                        )).toList(),
                order.getCreatedAt()
        );
    }
}
