package com.finartz.restaurantApi.service.aggregation.impl;

import com.finartz.restaurantApi.model.converter.dto.impl.UserDTOConverter;
import com.finartz.restaurantApi.model.dto.UserDto;
import com.finartz.restaurantApi.model.request.CreateUserRequest;
import com.finartz.restaurantApi.security.JwtResponse;
import com.finartz.restaurantApi.security.TokenManager;
import com.finartz.restaurantApi.service.aggregation.UserAggregationService;
import com.finartz.restaurantApi.service.foundation.UserFoundationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserAggregationServiceImpl implements UserAggregationService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final TokenManager tokenManager;
    private final UserDTOConverter userConverter;
    private final UserFoundationService userFoundationService;

    public UserAggregationServiceImpl(AuthenticationManager authenticationManager,
                                      UserDetailsService userDetailsService,
                                      TokenManager tokenManager,
                                      UserDTOConverter userConverter,
                                      UserFoundationService userFoundationService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.tokenManager = tokenManager;
        this.userConverter = userConverter;
        this.userFoundationService = userFoundationService;
    }

    @Override
    public UserDto saveUser(CreateUserRequest userRequest) {
        return userConverter.convertToDto(userFoundationService.saveUser(userRequest));
    }

    @Override
    public UserDto getUser(Long id){
        return userConverter.convertToDto(userFoundationService.getById(id));
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
        return userConverter.convertToDto(userFoundationService.getByUsername(userName));
    }
}
