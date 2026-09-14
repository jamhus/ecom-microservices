package com.app.ecomapplication.repository;

import com.app.ecomapplication.models.CartItem;
import com.app.ecomapplication.models.Product;
import com.app.ecomapplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByUserAndProduct(User user, Product productEntity);

    void deleteByUserAndProduct(User u, Product p);


    List<CartItem> findByUser(User user);

    void deleteByUser(User user);
}
