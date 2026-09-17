package com.ecommerce.user.services;

import com.ecommerce.user.models.Address;
import com.ecommerce.user.models.User;
import com.ecommerce.user.models.dtos.AddressDto;
import com.ecommerce.user.models.dtos.UserRequest;
import com.ecommerce.user.models.dtos.UserResponse;
import com.ecommerce.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
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

    public Optional<UserResponse> findUserById(String id) {
        var user = userRepository.findById(id)
                .map(this::mapToUserResponse);
        if(user.isEmpty()) {
            log.error("User with id {} not found", id);
            return Optional.empty();
        }
        log.info("User with id {} found", id);
        return user;
    }

    public boolean updateUser(String id, UserRequest model) {
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
            addressDto.setState(user.getAddress().getState());
            addressDto.setZipCode(user.getAddress().getZipCode());
            addressDto.setCountry(user.getAddress().getCountry());
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
            address.setState(userRequest.getAddress().getState());
            address.setZipCode(userRequest.getAddress().getZipCode());
            address.setCountry(userRequest.getAddress().getCountry());
            model.setAddress(address);
        }

    }
}
