package com.finartz.restaurantApi.service.aggregation;

import com.finartz.restaurantApi.model.dto.MenuDto;
import com.finartz.restaurantApi.model.request.CreateMealMenuRequest;
import com.finartz.restaurantApi.model.request.CreateRestaurantMenuRequest;

public interface MenuAggregationService {

    MenuDto saveRestaurantMenu(CreateRestaurantMenuRequest menuRequest);

    MenuDto getById(Long menuId);

    MenuDto saveMeal(CreateMealMenuRequest menuRequest);
}
