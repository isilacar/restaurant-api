package com.finartz.restaurantApi.service.aggregation;


import com.finartz.restaurantApi.model.dto.UserDto;
import com.finartz.restaurantApi.model.request.CreateUserRequest;
import com.finartz.restaurantApi.security.JwtResponse;

public interface UserService {

    UserDto saveUser(CreateUserRequest userRequest);

    UserDto getUser(Long id);

    JwtResponse createToken(CreateUserRequest userRequest);

    UserDto getByUsername(String userName);
}
