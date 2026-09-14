package com.app.ecomapplication.repository;

import com.app.ecomapplication.entity.Product;
import com.app.ecomapplication.entity.dto.ProductResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.active = true AND LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) AND p.stockQuantity > 0")
    List<Product> searchProduct(String keyword);

}
