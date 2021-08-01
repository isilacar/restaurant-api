package com.finartz.restaurantApi.service.aggregation;


import com.finartz.restaurantApi.model.dto.RestaurantDto;
import com.finartz.restaurantApi.model.enumeration.RestaurantStatus;
import com.finartz.restaurantApi.model.request.CreateRestaurantRequest;

import java.util.List;


public interface RestaurantService {

    RestaurantDto saveRestaurant(CreateRestaurantRequest createRestaurantRequest);

    RestaurantDto getById(Long id);

    List<RestaurantDto> getRestaurants(RestaurantStatus restaurantStatus);

    void updateRestaurant(Long id, CreateRestaurantRequest createRestaurantRequest);

}


// List<RestaurantDto> getAllNearestRestaurantByApproved();


//  List<RestaurantDto> getAllRestaurantByStatus(StatusEnum statusEnum);


// List<RestaurantDto> getAllRestaurantByApproved();

// void updateStatus(Long id, StatusEnum status);

//List<RestaurantDto> getAllRestaurantByAwaiting();
