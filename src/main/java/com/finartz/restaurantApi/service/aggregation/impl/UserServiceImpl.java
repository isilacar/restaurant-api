package com.finartz.restaurantApi.service.aggregation.impl;

import com.finartz.restaurantApi.model.converter.request.impl.UserRequestConverter;
import com.finartz.restaurantApi.model.dto.UserDto;
import com.finartz.restaurantApi.model.request.CreateUserRequest;
import com.finartz.restaurantApi.security.JwtResponse;
import com.finartz.restaurantApi.security.TokenManager;
import com.finartz.restaurantApi.service.aggregation.UserService;
import com.finartz.restaurantApi.service.foundation.UserFoundationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final TokenManager tokenManager;
    private final UserFoundationService userFoundationService;
    private final UserRequestConverter userRequestConverter;

    @Override
    public UserDto saveUser(CreateUserRequest userRequest) {
        return userFoundationService.saveUser(userRequestConverter.convertToDto(userRequest));
    }

    @Override
    public UserDto getUser(Long id){

        return userFoundationService.getUser(id);
    }

    @Override
    public JwtResponse createToken(CreateUserRequest userRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUserName(),
                userRequest.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(userRequest.getUserName());
        String token = tokenManager.generateJwtToken(userDetails);
        return new JwtResponse(token);
    }

    @Override
    public UserDto getByUsername(String userName) {
        return userFoundationService.getByUsername(userName);
    }
}
