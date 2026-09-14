package com.ecommerce.user.controllers;
import com.ecommerce.user.models.dtos.UserRequest;
import com.ecommerce.user.models.dtos.UserResponse;
import com.ecommerce.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> GetUserByID(@PathVariable String id) {
        return userService
                .findUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping
    public List<UserResponse> GetAllUsers() {
        return userService.fetchAllUsers();
    }

    @PostMapping
    public void CreateUser(@RequestBody UserRequest user) {

        userService.createUser(user);
    }

    @PutMapping("/{id}")
    public boolean updateUser(@PathVariable String id, @RequestBody UserRequest model) {
        return userService.updateUser(id, model);
    }

}
