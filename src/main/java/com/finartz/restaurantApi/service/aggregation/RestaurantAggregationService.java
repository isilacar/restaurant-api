package com.finartz.restaurantApi.service.aggregation;


import com.finartz.restaurantApi.model.dto.RestaurantDto;
import com.finartz.restaurantApi.model.enumeration.StatusEnum;
import com.finartz.restaurantApi.model.request.CreateRestaurantRequest;

import java.util.List;


public interface RestaurantAggregationService {

    RestaurantDto saveRestaurant(CreateRestaurantRequest createRestaurantRequest);

    RestaurantDto getById(Long id);

   List<RestaurantDto> getAllNearestRestaurantByApproved();

    void updateRestaurant(Long id,CreateRestaurantRequest createRestaurantRequest);

    List<RestaurantDto> getAllRestaurantByStatus(StatusEnum statusEnum);


    // List<RestaurantDto> getAllRestaurantByApproved();

    // void updateStatus(Long id, StatusEnum status);

    //List<RestaurantDto> getAllRestaurantByAwaiting();

}
