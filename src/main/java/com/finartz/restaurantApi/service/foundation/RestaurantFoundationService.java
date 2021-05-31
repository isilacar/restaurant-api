package com.finartz.restaurantApi.service.foundation;

import com.finartz.restaurantApi.model.entity.RestaurantEntity;
import com.finartz.restaurantApi.model.enumeration.StatusEnum;
import com.finartz.restaurantApi.model.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantFoundationService {
    RestaurantEntity saveRestaurant(CreateRestaurantRequest createRestaurantRequest);

    RestaurantEntity getById(Long id);

    List<RestaurantEntity> getAllNearestRestaurantByApproved();

    void updateRestaurant(Long id,CreateRestaurantRequest createRestaurantRequest);

    List<RestaurantEntity> getAllRestaurantByStatus(StatusEnum statusEnum);

    //  List<RestaurantEntity> getAllRestaurantByApproved();

    //   void updateStatus(Long id, StatusEnum status);

    // List<RestaurantEntity> getAllRestaurantByAwaiting();
}
