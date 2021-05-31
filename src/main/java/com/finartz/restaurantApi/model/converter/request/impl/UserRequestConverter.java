package com.finartz.restaurantApi.model.converter.request.impl;

import com.finartz.restaurantApi.model.entity.UserEntity;
import com.finartz.restaurantApi.model.request.CreateUserRequest;
import org.springframework.stereotype.Component;


@Component
public class UserRequestConverter {


    public UserEntity convertToEntity(CreateUserRequest createUserRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(createUserRequest.getId());
        userEntity.setCreateDate(createUserRequest.getCreateDate());
        userEntity.setUpdateDate(createUserRequest.getUpdateDate());
        userEntity.setRole(createUserRequest.getRole());
        userEntity.setUserName(createUserRequest.getUserName());
        userEntity.setPassword(createUserRequest.getPassword());
        userEntity.setLatitude(createUserRequest.getLatitude());
        userEntity.setLongitude(createUserRequest.getLongitude());
        userEntity.setEMail(createUserRequest.getEMail());
        return userEntity;

    }


    public CreateUserRequest convertToRequest(UserEntity userEntity) {
        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setId(userEntity.getId());
        userRequest.setCreateDate(userEntity.getCreateDate());
        userRequest.setUpdateDate(userEntity.getUpdateDate());
        userRequest.setRole(userEntity.getRole());
        userRequest.setUserName(userEntity.getUserName());
        userRequest.setPassword(userEntity.getPassword());
        userRequest.setLatitude(userEntity.getLatitude());
        userRequest.setLongitude(userEntity.getLongitude());
        userRequest.setEMail(userRequest.getEMail());
        return userRequest;
    }
}
