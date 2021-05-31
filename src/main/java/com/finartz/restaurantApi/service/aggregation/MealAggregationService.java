package com.finartz.restaurantApi.service.aggregation;

import com.finartz.restaurantApi.model.dto.MealDto;
import com.finartz.restaurantApi.model.request.CreateMealRequest;

import java.util.List;

public interface MealAggregationService {
    MealDto saveMeal(CreateMealRequest mealRequest);

    MealDto getById(Long mealId);

    List<MealDto> getByMealName(String mealName);

}
