package com.finartz.restaurantApi.service.foundation;


import com.finartz.restaurantApi.model.dto.MealDto;

import java.util.List;

public interface MealFoundationService {

   MealDto saveMeal(MealDto mealDto);

    MealDto getMeal(Long id);

    List<MealDto> getByName(String name);
}
