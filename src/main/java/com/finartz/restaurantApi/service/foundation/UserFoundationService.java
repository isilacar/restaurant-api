package com.finartz.restaurantApi.service.foundation;

import com.finartz.restaurantApi.model.entity.UserEntity;
import com.finartz.restaurantApi.model.request.CreateUserRequest;

public interface UserFoundationService {
    UserEntity getById(Long id);

    UserEntity getByUsername(String userName);

    UserEntity saveUser(CreateUserRequest userRequest);
}
