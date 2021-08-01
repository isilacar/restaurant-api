package com.finartz.restaurantApi.service.aggregation;

import com.finartz.restaurantApi.model.dto.MealDto;
import com.finartz.restaurantApi.model.request.CreateMealRequest;

import java.util.List;

public interface MealService {
    MealDto saveMeal(CreateMealRequest mealRequest);

    MealDto getMeal(Long mealId);

    List<MealDto> getByMealName(String mealName);

}
