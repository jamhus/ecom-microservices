package com.app.ecomapplication.entity.dto;

import com.app.ecomapplication.entity.UserRole;
import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRole role;

    private AddressDto address;
}
