package com.ecommerce.notifications.config;

import com.ecommerce.notifications.payload.OrderCreatedEvent;
import com.ecommerce.notifications.payload.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer {
    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void handleOrderEvent(OrderCreatedEvent orderEvent) {

        long orderId = orderEvent.getOrderId();
        OrderStatus orderStatus = orderEvent.getOrderStatus();

        System.out.println("Order Status: " + orderStatus);

        // Update Database
        // Send Notification
        // Send Emails
        // Generate Invoice
        // Send Seller Notification

    }
}