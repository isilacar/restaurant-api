package com.finartz.restaurantApi.service.aggregation.impl;

import com.finartz.restaurantApi.model.converter.dto.impl.MealMenuDtoConverter;
import com.finartz.restaurantApi.model.converter.dto.impl.RestaurantMenuDtoConverter;
import com.finartz.restaurantApi.model.dto.MealMenuDto;
import com.finartz.restaurantApi.model.dto.MenuDto;
import com.finartz.restaurantApi.model.dto.RestaurantMenuDto;
import com.finartz.restaurantApi.model.request.CreateRestaurantMenuRequest;
import com.finartz.restaurantApi.model.request.MealMenuRequest;
import com.finartz.restaurantApi.service.aggregation.MenuService;
import com.finartz.restaurantApi.service.foundation.MenuFoundationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class MenuServiceImpl implements MenuService {

    private final MenuFoundationService menuFoundationService;
    private final RestaurantMenuDtoConverter restaurantMenuDtoConverter;
    private final MealMenuDtoConverter mealMenuDtoConverter;

    @Override
    public RestaurantMenuDto saveRestaurantMenu(CreateRestaurantMenuRequest menuRequest) {
        RestaurantMenuDto restaurantMenuDto = restaurantMenuDtoConverter.convert(menuRequest);
        return menuFoundationService.saveRestaurantMenu(restaurantMenuDto);
    }

    @Override
    public MenuDto getById(Long menuId) {
        return menuFoundationService.getMenu(menuId);
    }

    @Override
    public MealMenuDto saveMeal(MealMenuRequest mealMenuRequest) {
        MealMenuDto mealMenuDto = mealMenuDtoConverter.convert(mealMenuRequest);
        return menuFoundationService.saveMeal(mealMenuDto);

    }
}
