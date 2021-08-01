package com.finartz.restaurantApi.model.converter.dto.impl;

import com.finartz.restaurantApi.model.converter.dto.BaseDTOConverter;
import com.finartz.restaurantApi.model.dto.RestaurantMenuDto;
import com.finartz.restaurantApi.model.entity.MenuEntity;
import com.finartz.restaurantApi.model.request.CreateRestaurantMenuRequest;
import com.finartz.restaurantApi.service.aggregation.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantMenuDtoConverter implements BaseDTOConverter<MenuEntity, RestaurantMenuDto> {
    private final RestaurantService restaurantService;
    private final RestaurantDTOConverter restaurantDTOConverter;
    private final MealDTOConverter mealDTOConverter;

    public RestaurantMenuDto convert(CreateRestaurantMenuRequest restaurantMenuRequest) {
        if (restaurantMenuRequest == null) {
            return null;
        }
        RestaurantMenuDto restaurantMenuDto = new RestaurantMenuDto();
        restaurantMenuDto.setId(restaurantMenuRequest.getId());
        restaurantMenuDto.setCreateDate(restaurantMenuRequest.getCreateDate());
        restaurantMenuDto.setUpdateDate(restaurantMenuRequest.getUpdateDate());
        restaurantMenuDto.setRestaurantId(restaurantMenuRequest.getRestaurantId());
        if (restaurantMenuDto.getMeals() != null) {
            restaurantMenuDto.setMeals(restaurantMenuDto.getMeals());
        }

        return restaurantMenuDto;
    }

    @Override
    public MenuEntity convertToEntity(RestaurantMenuDto dto) {
        if (dto == null) {
            return null;
        }
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setId(dto.getId());
        menuEntity.setCreateDate(dto.getCreateDate());
        menuEntity.setUpdateDate(dto.getUpdateDate());
        menuEntity.setRestaurant(restaurantDTOConverter.convertToEntity(restaurantService.getById(dto.getRestaurantId())));
        return menuEntity;
    }

    @Override
    public RestaurantMenuDto convertToDto(MenuEntity entity) {
        RestaurantMenuDto menuDto=new RestaurantMenuDto();
        menuDto.setId(entity.getId());
        menuDto.setRestaurantId(entity.getRestaurant().getId());
        menuDto.setUpdateDate(entity.getUpdateDate());
        menuDto.setCreateDate(entity.getCreateDate());
        menuDto.setMeals(mealDTOConverter.convertToDtoList(entity.getMeals()));

        return menuDto;
    }
}
