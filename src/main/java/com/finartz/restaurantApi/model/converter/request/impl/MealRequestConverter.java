package com.finartz.restaurantApi.model.converter.request.impl;

import com.finartz.restaurantApi.model.converter.request.BaseRequestConverter;
import com.finartz.restaurantApi.model.entity.MealEntity;
import com.finartz.restaurantApi.model.request.CreateMealRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class MealRequestConverter implements BaseRequestConverter<MealEntity, CreateMealRequest> {

    @Override
    public MealEntity convertToEntity(CreateMealRequest createMealRequest) {
        MealEntity mealEntity=new MealEntity();
        mealEntity.setId(createMealRequest.getId());
        mealEntity.setCreateDate(createMealRequest.getCreateDate());
        mealEntity.setUpdateDate(createMealRequest.getUpdateDate());
        mealEntity.setName(createMealRequest.getName().toLowerCase(new Locale("tr", "TR")));
        mealEntity.setPrice(createMealRequest.getPrice());
        return mealEntity;
    }

    @Override
    public CreateMealRequest convertToRequest(MealEntity mealEntity) {
        CreateMealRequest mealRequest=new CreateMealRequest();
        mealRequest.setId(mealEntity.getId());
        mealRequest.setCreateDate(mealEntity.getCreateDate());
        mealRequest.setUpdateDate(mealEntity.getUpdateDate());
        mealRequest.setName(mealEntity.getName().toLowerCase());
        mealRequest.setPrice(mealEntity.getPrice());
        return mealRequest;
    }
    public List<MealEntity> convertToEntityList(List<CreateMealRequest> mealRequests){
        return mealRequests.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
