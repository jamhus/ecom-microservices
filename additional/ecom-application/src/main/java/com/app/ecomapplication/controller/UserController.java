package com.app.ecomapplication.controller;

import com.app.ecomapplication.entity.dto.UserRequest;
import com.app.ecomapplication.entity.dto.UserResponse;
import com.app.ecomapplication.service.UserService;
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
    public ResponseEntity<UserResponse> GetUserByID(@PathVariable Long id) {
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
    public boolean updateUser(@PathVariable Long id, @RequestBody UserRequest model) {
        return userService.updateUser(id, model);
    }

}
