package com.finartz.restaurantApi.service.foundation.impl;

import com.finartz.restaurantApi.dao.UserRepository;
import com.finartz.restaurantApi.model.converter.request.impl.UserRequestConverter;
import com.finartz.restaurantApi.model.entity.UserEntity;
import com.finartz.restaurantApi.model.request.CreateUserRequest;
import com.finartz.restaurantApi.service.foundation.UserFoundationService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserFoundationServiceImpl implements UserFoundationService {

    private final UserRepository userRepository;
    private final UserRequestConverter userRequestConverter;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserFoundationServiceImpl(UserRepository userRepository,
                                     UserRequestConverter userRequestConverter,
                                     BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userRequestConverter = userRequestConverter;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserEntity getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserEntity getByUsername(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public UserEntity saveUser(CreateUserRequest userRequest) {
        String encodedPassword = bCryptPasswordEncoder.encode(userRequest.getPassword());
        userRequest.setPassword(encodedPassword);

        if (userRepository.findByUserName(userRequest.getUserName()) != null) {
            throw new RuntimeException("This username is already exist..");
        }
        UserEntity userEntity = userRequestConverter.convertToEntity(userRequest);
        return userRepository.saveUser(userEntity);
    }
}
