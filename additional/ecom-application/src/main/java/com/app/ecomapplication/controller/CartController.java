package com.app.ecomapplication.controller;

import com.app.ecomapplication.entity.CartItem;
import com.app.ecomapplication.entity.dto.CartItemRequest;
import com.app.ecomapplication.entity.dto.CartItemResponse;
import com.app.ecomapplication.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<String> addToCart(
            @RequestHeader("X-User-ID") String userId,
            @RequestBody CartItemRequest cartItemRequest
    ) {
        if(cartService.addToCart(userId, cartItemRequest))
            return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.badRequest().body("Product is out of stock or not found");
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<String> deleteItemFromCart(
            @RequestHeader("X-User-ID") String userId,
            @PathVariable Long productId
    ) {
        if(cartService.deleteItemFromCart(userId, productId))
            return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.badRequest().body("Product is not found in cart");
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getCartItems(
            @RequestHeader("X-User-ID") String userId
    ) {
        var cartItems =  cartService.fetchCartItems(userId);
        return ResponseEntity.ok(cartItems);
    }
}
