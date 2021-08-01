package com.finartz.restaurantApi.model.converter.dto.impl;

import com.finartz.restaurantApi.model.converter.dto.BaseDTOConverter;
import com.finartz.restaurantApi.model.dto.MealDto;
import com.finartz.restaurantApi.model.entity.MealEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class MealDTOConverter implements BaseDTOConverter<MealEntity, MealDto> {

    @Override
    public MealEntity convertToEntity(com.finartz.restaurantApi.model.dto.MealDto dto) {
        MealEntity mealEntity = new MealEntity();
        mealEntity.setId(dto.getId());
        mealEntity.setCreateDate(dto.getCreateDate());
        mealEntity.setUpdateDate(dto.getUpdateDate());
        mealEntity.setName(dto.getName().toLowerCase(new Locale("tr", "TR")));
        mealEntity.setPrice(dto.getPrice());

        return mealEntity;
    }

    @Override
    public MealDto convertToDto(MealEntity entity) {
        com.finartz.restaurantApi.model.dto.MealDto mealDto = new com.finartz.restaurantApi.model.dto.MealDto();
        mealDto.setId(entity.getId());
        mealDto.setCreateDate(entity.getCreateDate());
        mealDto.setUpdateDate(entity.getUpdateDate());
        mealDto.setName(entity.getName().toLowerCase());
        mealDto.setPrice(entity.getPrice());
        return mealDto;
    }

    public List<MealDto> convertToDtoList(List<MealEntity> mealEntities) {
        return mealEntities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<MealEntity> convertToEntityList(List<MealDto> mealDtos) {
        return mealDtos.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
