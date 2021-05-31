package com.finartz.restaurantApi.model.converter.request.impl;

import com.finartz.restaurantApi.model.converter.request.BaseRequestConverter;
import com.finartz.restaurantApi.model.entity.RestaurantEntity;
import com.finartz.restaurantApi.model.request.CreateRestaurantRequest;
import org.springframework.stereotype.Component;

@Component
public class RestaurantRequestConverter implements BaseRequestConverter<RestaurantEntity, CreateRestaurantRequest> {

    @Override
    public RestaurantEntity convertToEntity(CreateRestaurantRequest createRestaurantRequest) {
        RestaurantEntity restaurantEntity=new RestaurantEntity();
        restaurantEntity.setId(createRestaurantRequest.getId());
        restaurantEntity.setName(createRestaurantRequest.getName());
        restaurantEntity.setCreateDate(createRestaurantRequest.getCreateDate());
        restaurantEntity.setUpdateDate(createRestaurantRequest.getUpdateDate());
        restaurantEntity.setStatus(createRestaurantRequest.getStatus());
        restaurantEntity.setLatitude(createRestaurantRequest.getLatitude());
        restaurantEntity.setLongitude(createRestaurantRequest.getLongitude());

        return restaurantEntity;

    }

    @Override
    public CreateRestaurantRequest convertToRequest(RestaurantEntity restaurantEntity) {
       CreateRestaurantRequest createRestaurantRequest=new CreateRestaurantRequest();
        createRestaurantRequest.setId(restaurantEntity.getId());
        createRestaurantRequest.setName(restaurantEntity.getName());
        createRestaurantRequest.setCreateDate(restaurantEntity.getCreateDate());
        createRestaurantRequest.setUpdateDate(restaurantEntity.getUpdateDate());
        createRestaurantRequest.setStatus(restaurantEntity.getStatus());
        createRestaurantRequest.setLatitude(restaurantEntity.getLatitude());
        createRestaurantRequest.setLongitude(restaurantEntity.getLongitude());

        return createRestaurantRequest;
     
    }
}
