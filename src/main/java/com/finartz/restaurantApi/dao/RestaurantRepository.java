package com.finartz.restaurantApi.dao;

import com.finartz.restaurantApi.model.entity.RestaurantEntity;
import com.finartz.restaurantApi.model.enumeration.RestaurantStatus;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository {

    RestaurantEntity saveRestaurant(RestaurantEntity restaurantEntity);

    Optional<RestaurantEntity> findRestaurant(Long id);

    List<RestaurantEntity> findNearestRestaurantByStatusApproved(Long userId);

    void updateRestaurant(Long id, RestaurantEntity restaurantEntity);

    List<RestaurantEntity> findRestaurantByStatus(RestaurantStatus statusEnum);

    List<RestaurantEntity> getAll();
}
