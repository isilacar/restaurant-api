package com.finartz.restaurantApi.service.foundation.impl;

import com.finartz.restaurantApi.dao.UserRepository;
import com.finartz.restaurantApi.error.UserError;
import com.finartz.restaurantApi.exception.ResourceNotFoundException;
import com.finartz.restaurantApi.model.converter.dto.impl.UserDTOConverter;
import com.finartz.restaurantApi.model.dto.UserDto;
import com.finartz.restaurantApi.model.entity.UserEntity;
import com.finartz.restaurantApi.service.foundation.UserFoundationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFoundationServiceImpl implements UserFoundationService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDTOConverter userDTOConverter;

    @Override
    public UserDto getUser(Long id) {
        UserEntity userEntity = userRepository.findUser(id).orElseThrow(() -> new ResourceNotFoundException(UserError.USER_NOT_FOUND));
        return userDTOConverter.convertToDto(userEntity);

    }

    @Override
    public UserDto getByUsername(String userName) {
        UserEntity userEntity = userRepository.findByUserName(userName).orElseThrow(() -> new ResourceNotFoundException(UserError.USER_NOT_FOUND));
        return userDTOConverter.convertToDto(userEntity);

    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        String encodedPassword = bCryptPasswordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPassword);

        UserEntity userEntity = userRepository.saveUser(userDTOConverter.convertToEntity(userDto));
        return userDTOConverter.convertToDto(userEntity);
    }
}
