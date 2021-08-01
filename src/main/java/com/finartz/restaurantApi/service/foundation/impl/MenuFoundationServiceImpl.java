package com.finartz.restaurantApi.service.foundation.impl;

import com.finartz.restaurantApi.dao.MenuRepository;
import com.finartz.restaurantApi.error.MenuError;
import com.finartz.restaurantApi.exception.ResourceNotFoundException;
import com.finartz.restaurantApi.model.converter.dto.impl.*;
import com.finartz.restaurantApi.model.dto.*;
import com.finartz.restaurantApi.model.entity.MealEntity;
import com.finartz.restaurantApi.model.entity.MenuEntity;
import com.finartz.restaurantApi.model.entity.RestaurantEntity;
import com.finartz.restaurantApi.service.foundation.MealFoundationService;
import com.finartz.restaurantApi.service.foundation.MenuFoundationService;
import com.finartz.restaurantApi.service.foundation.RestaurantFoundationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuFoundationServiceImpl implements MenuFoundationService {

    private final MenuRepository menuRepository;
    private final RestaurantFoundationService restaurantFoundationService;
    private final MealFoundationService mealFoundationService;
    private final MealDTOConverter mealDTOConverter;
    private final MenuDTOConverter menuDTOConverter;
    private final RestaurantDTOConverter restaurantDTOConverter;
    private final RestaurantMenuDtoConverter restaurantMenuDtoConverter;
    private final MealMenuDtoConverter mealMenuDtoConverter;

    @Transactional
    @Override
    public RestaurantMenuDto saveRestaurantMenu(RestaurantMenuDto restaurantMenu) {
        Long restaurantId = restaurantMenu.getRestaurantId();
        RestaurantDto restaurantDto = restaurantFoundationService.getRestaurant(restaurantId);

        RestaurantEntity restaurantEntity = restaurantDTOConverter.convertToEntity(restaurantDto);
        MenuEntity menuEntity = menuDTOConverter.convertToEntity(restaurantMenu);
        menuEntity.setRestaurant(restaurantEntity);
        menuRepository.saveMenu(menuEntity);
        return restaurantMenuDtoConverter.convertToDto(menuEntity);
    }

    @Override
    public MenuDto getMenu(Long menuId) {
        MenuEntity menuEntity = menuRepository.findMenu(menuId).orElseThrow(() -> new ResourceNotFoundException(MenuError.MENU_NOT_FOUND));
        return menuDTOConverter.convertToDto(menuEntity);

    }

    @Transactional
    @Override
    public MealMenuDto saveMeal(MealMenuDto mealMenu) {
        Long mealId = mealMenu.getMealId();
        Long menuId = mealMenu.getMenuId();

        MealDto mealDto = mealFoundationService.getMeal(mealId);

        MealEntity mealEntity = mealDTOConverter.convertToEntity(mealDto);
        Optional<MenuEntity> optionalMenuEntity = menuRepository.findMenu(menuId);

        optionalMenuEntity.get().getMeals().add(mealEntity);
        MenuEntity menuEntity = menuRepository.saveMealToMenu(optionalMenuEntity.get());

        return mealMenuDtoConverter.convertToDto(menuEntity);

    }
}
