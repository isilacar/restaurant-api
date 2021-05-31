package com.finartz.restaurantApi.dao;

import com.finartz.restaurantApi.model.entity.UserEntity;

public interface UserRepository {

    UserEntity saveUser(UserEntity userEntity);
    UserEntity findById(Long id);
    UserEntity findByUserName(String userName);

}
