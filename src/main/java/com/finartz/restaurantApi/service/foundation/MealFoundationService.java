package com.finartz.restaurantApi.service.foundation;

import com.finartz.restaurantApi.model.entity.MealEntity;
import com.finartz.restaurantApi.model.request.CreateMealRequest;

import java.util.List;

public interface MealFoundationService {

    MealEntity saveMeal(CreateMealRequest mealRequest);

    MealEntity getById(Long id);

    List<MealEntity> getByName(String name);
}
