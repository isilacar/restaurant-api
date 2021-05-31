package com.finartz.restaurantApi.service.aggregation.impl;

import com.finartz.restaurantApi.model.converter.dto.impl.MenuDTOConverter;
import com.finartz.restaurantApi.model.dto.MenuDto;
import com.finartz.restaurantApi.model.request.CreateMealMenuRequest;
import com.finartz.restaurantApi.model.request.CreateRestaurantMenuRequest;
import com.finartz.restaurantApi.service.aggregation.MenuAggregationService;
import com.finartz.restaurantApi.service.foundation.MenuFoundationService;
import org.springframework.stereotype.Service;

@Service
public class MenuAggregationServiceImpl implements MenuAggregationService {

    private final MenuDTOConverter menuConverter;
    private final MenuFoundationService menuFoundationService;

    public MenuAggregationServiceImpl(MenuDTOConverter menuConverter,
                                      MenuFoundationService menuFoundationService) {
        this.menuConverter = menuConverter;
        this.menuFoundationService = menuFoundationService;
    }

    @Override
    public MenuDto saveRestaurantMenu(CreateRestaurantMenuRequest menuRequest) {
        return menuConverter.convertToDto(menuFoundationService.saveRestaurantMenu(menuRequest));
    }

    @Override
    public MenuDto getById(Long menuId) {
        return menuConverter.convertToDto(menuFoundationService.getById(menuId));
    }

    @Override
    public MenuDto saveMeal(CreateMealMenuRequest menuRequest) {
        return menuConverter.convertToDto(menuFoundationService.saveMeal(menuRequest));

    }
}
