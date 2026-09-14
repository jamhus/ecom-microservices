package com.app.ecomapplication.service;

import com.app.ecomapplication.entity.Address;
import com.app.ecomapplication.entity.dto.AddressDto;
import com.app.ecomapplication.entity.dto.UserRequest;
import com.app.ecomapplication.entity.dto.UserResponse;
import com.app.ecomapplication.repository.UserRepository;
import com.app.ecomapplication.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserResponse> fetchAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    public void createUser(UserRequest userRequest) {
        var user = new User();
        MapToUser(user,userRequest);
        userRepository.save(user);
    }

    public Optional<UserResponse> findUserById(Long id) {

        return userRepository.findById(id)
                .map(this::mapToUserResponse);
    }

    public boolean updateUser(Long id, UserRequest model) {
        return userRepository.findById(id)
            .map(user -> {
                MapToUser(user,model);
                userRepository.save(user);
                return true;
            })
            .orElse(false);
    }

    private UserResponse mapToUserResponse(User user) {
        var response =  new UserResponse();
        response.setId(String.valueOf(user.getId()));
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setPhone(user.getPhone());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());

        if(user.getAddress() != null) {
            var addressDto = new AddressDto();
            addressDto.setStreet(user.getAddress().getStreet());
            addressDto.setCity(user.getAddress().getCity());
            addressDto.setZipCode(user.getAddress().getZipCode());
            response.setAddress(addressDto);
        }
        return response;
    }

    private void MapToUser(User model, UserRequest userRequest) {
        model.setFirstName(userRequest.getFirstName());
        model.setLastName(userRequest.getLastName());
        model.setPhone(userRequest.getPhone());
        model.setEmail(userRequest.getEmail());

        if(userRequest.getAddress() != null) {
            var address = new Address();
            address.setStreet(userRequest.getAddress().getStreet());
            address.setCity(userRequest.getAddress().getCity());
            address.setZipCode(userRequest.getAddress().getZipCode());
            model.setAddress(address);
        }

    }
}
