package com.finartz.restaurantApi.dao;

import com.finartz.restaurantApi.model.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {

    UserEntity saveUser(UserEntity userEntity);
    Optional<UserEntity> findUser(Long id);
    Optional<UserEntity> findByUserName(String userName);

}
