package com.ecommerce.user.controllers;
import com.ecommerce.user.models.dtos.UserRequest;
import com.ecommerce.user.models.dtos.UserResponse;
import com.ecommerce.user.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> GetUserByID(@PathVariable String id) {
        log.info("Fetching user with id: {}", id);
        return userService
                .findUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping
    public List<UserResponse> GetAllUsers() {
        log.info("Fetching all users");
        return userService.fetchAllUsers();
    }

    @PostMapping
    public void CreateUser(@RequestBody UserRequest user) {
        log.info("Creating user with details: {}", user);
        userService.createUser(user);
    }

    @PutMapping("/{id}")
    public boolean updateUser(@PathVariable String id, @RequestBody UserRequest model) {
        log.info("Updating user with id: {} and details: {}", id, model);
        return userService.updateUser(id, model);
    }

}
