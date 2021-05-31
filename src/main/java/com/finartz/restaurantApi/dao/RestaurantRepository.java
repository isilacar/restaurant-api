package com.finartz.restaurantApi.dao;

import com.finartz.restaurantApi.model.entity.RestaurantEntity;
import com.finartz.restaurantApi.model.enumeration.StatusEnum;

import java.util.List;

public interface RestaurantRepository {

    RestaurantEntity saveRestaurant(RestaurantEntity restaurantEntity);

    RestaurantEntity findById(Long id);

    List<RestaurantEntity> findNearestRestaurantByStatusApproved(Long userId);

    void updateRestaurant(Long id, RestaurantEntity restaurantEntity);

    List<RestaurantEntity> findRestaurantByStatus(StatusEnum statusEnum);

    //  List<RestaurantEntity> findRestaurantByStatusApproved();

    // void updateStatus(Long restaurantId, StatusEnum status);

    //  List<RestaurantEntity> findRestaurantByStatusAwaiting();


}
