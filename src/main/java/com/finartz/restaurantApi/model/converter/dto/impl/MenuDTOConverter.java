package com.finartz.restaurantApi.model.converter.dto.impl;

import com.finartz.restaurantApi.model.converter.dto.BaseDTOConverter;
import com.finartz.restaurantApi.model.dto.MenuDto;
import com.finartz.restaurantApi.model.entity.MenuEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MenuDTOConverter implements BaseDTOConverter<MenuEntity, MenuDto> {

    private final MealDTOConverter mealConverter;

    public MenuDTOConverter(MealDTOConverter mealConverter) {
        this.mealConverter = mealConverter;
    }

    @Override
    public MenuEntity convertToEntity(MenuDto dto) {
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setId(dto.getId());
        menuEntity.setCreateDate(dto.getCreateDate());
        menuEntity.setUpdateDate(dto.getUpdateDate());
        menuEntity.setMeals(mealConverter.convertToEntityList(dto.getMeals()));

        return menuEntity;
    }

    @Override
    public MenuDto convertToDto(MenuEntity entity) {
        MenuDto menuDto = new MenuDto();
        menuDto.setId(entity.getId());
        menuDto.setCreateDate(entity.getCreateDate());
        menuDto.setUpdateDate(entity.getUpdateDate());
        if(entity.getMeals()!=null){
            menuDto.setMeals(mealConverter.convertToDtoList(entity.getMeals()));
        }

        return menuDto;
    }

    public List<MenuDto> convertToDtoList(List<MenuEntity> menuEntityList) {
        return menuEntityList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<MenuEntity> convertToEntityList(List<MenuDto> menuDtos) {
        return menuDtos.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
