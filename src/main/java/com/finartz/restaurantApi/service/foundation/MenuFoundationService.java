package com.finartz.restaurantApi.service.foundation;

import com.finartz.restaurantApi.model.dto.MealMenuDto;
import com.finartz.restaurantApi.model.dto.MenuDto;
import com.finartz.restaurantApi.model.dto.RestaurantMenuDto;

public interface MenuFoundationService {

    RestaurantMenuDto saveRestaurantMenu(RestaurantMenuDto restaurantMenu);

    MenuDto getMenu(Long menuId);

    MealMenuDto saveMeal(MealMenuDto mealMenuDto);
}
