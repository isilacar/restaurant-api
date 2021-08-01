package com.finartz.restaurantApi.service.aggregation;

import com.finartz.restaurantApi.model.dto.MealMenuDto;
import com.finartz.restaurantApi.model.dto.MenuDto;
import com.finartz.restaurantApi.model.dto.RestaurantMenuDto;
import com.finartz.restaurantApi.model.request.CreateRestaurantMenuRequest;
import com.finartz.restaurantApi.model.request.MealMenuRequest;

public interface MenuService {

    RestaurantMenuDto saveRestaurantMenu(CreateRestaurantMenuRequest menuRequest);

    MenuDto getById(Long menuId);

    MealMenuDto saveMeal(MealMenuRequest mealMenuRequest);
}
