package com.finartz.restaurantApi.service.foundation.impl;

import com.finartz.restaurantApi.dao.MenuRepository;
import com.finartz.restaurantApi.model.converter.request.impl.MenuRequestConverter;
import com.finartz.restaurantApi.model.entity.MealEntity;
import com.finartz.restaurantApi.model.entity.MenuEntity;
import com.finartz.restaurantApi.model.entity.RestaurantEntity;
import com.finartz.restaurantApi.model.request.CreateMealMenuRequest;
import com.finartz.restaurantApi.model.request.CreateRestaurantMenuRequest;
import com.finartz.restaurantApi.service.foundation.MealFoundationService;
import com.finartz.restaurantApi.service.foundation.MenuFoundationService;
import com.finartz.restaurantApi.service.foundation.RestaurantFoundationService;
import org.springframework.stereotype.Service;

@Service
public class MenuFoundationServiceImpl implements MenuFoundationService {

    private final MenuRepository menuRepository;
    private final RestaurantFoundationService restaurantFoundationService;
    private final MenuRequestConverter menuRequestConverter;
    private final MealFoundationService mealFoundationService;

    public MenuFoundationServiceImpl(MenuRepository menuRepository,
                                     RestaurantFoundationService restaurantFoundationService,
                                     MenuRequestConverter menuRequestConverter, MealFoundationService mealFoundationService) {
        this.menuRepository = menuRepository;
        this.restaurantFoundationService = restaurantFoundationService;
        this.menuRequestConverter = menuRequestConverter;
        this.mealFoundationService = mealFoundationService;
    }

    @Override
    public MenuEntity saveRestaurantMenu(CreateRestaurantMenuRequest menuRequest) {
        RestaurantEntity restaurantEntity = restaurantFoundationService.getById(menuRequest.getRestaurantId());
        MenuEntity menuEntity = menuRequestConverter.convertToEntity(menuRequest);
        menuEntity.setRestaurant(restaurantEntity);
        return menuRepository.saveMenu(menuEntity);
    }


    @Override
    public MenuEntity getById(Long menuId) {
        return menuRepository.findById(menuId);
    }

    @Override
    public MenuEntity saveMeal(CreateMealMenuRequest menuRequest) {
        MealEntity mealEntity = mealFoundationService.getById(menuRequest.getMealId());
        MenuEntity menuEntity = menuRepository.findById(menuRequest.getId());
        menuEntity.getMeals().add(mealEntity);
        return menuRepository.saveMealToMenu(menuEntity);

    }
}
