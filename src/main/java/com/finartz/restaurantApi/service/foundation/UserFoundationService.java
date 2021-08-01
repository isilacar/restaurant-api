package com.finartz.restaurantApi.service.foundation;

import com.finartz.restaurantApi.model.dto.UserDto;

public interface UserFoundationService {
    UserDto getUser(Long id);

    UserDto getByUsername(String userName);

    UserDto saveUser(UserDto userDto);
}
