package com.finartz.restaurantApi.service.foundation;

import com.finartz.restaurantApi.model.dto.RestaurantDto;
import com.finartz.restaurantApi.model.enumeration.RestaurantStatus;

import java.util.List;

public interface RestaurantFoundationService {
    RestaurantDto saveRestaurant(RestaurantDto restaurant);

    RestaurantDto getRestaurant(Long id);

    List<RestaurantDto> getAllNearestRestaurantByApproved();

    void updateRestaurant(Long id,RestaurantDto restaurantDto);

    List<RestaurantDto> getAllRestaurantByStatus(RestaurantStatus statusEnum);

    List<RestaurantDto> getAllRestaurants();

    //  List<RestaurantEntity> getAllRestaurantByApproved();

    //   void updateStatus(Long id, StatusEnum status);

    // List<RestaurantEntity> getAllRestaurantByAwaiting();
}
