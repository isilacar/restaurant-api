package com.finartz.restaurantApi.service.foundation;

import com.finartz.restaurantApi.model.entity.MenuEntity;
import com.finartz.restaurantApi.model.request.CreateMealMenuRequest;
import com.finartz.restaurantApi.model.request.CreateRestaurantMenuRequest;

public interface MenuFoundationService {

    MenuEntity saveRestaurantMenu(CreateRestaurantMenuRequest menuRequest);

    MenuEntity getById(Long menuId);

    MenuEntity saveMeal(CreateMealMenuRequest menuRequest);
}
