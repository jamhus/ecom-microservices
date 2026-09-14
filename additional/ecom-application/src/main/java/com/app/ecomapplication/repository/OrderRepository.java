package com.app.ecomapplication.repository;

import com.app.ecomapplication.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
