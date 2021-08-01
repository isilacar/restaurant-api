package com.finartz.restaurantApi.service.aggregation.impl;

import com.finartz.restaurantApi.model.converter.request.impl.MealRequestConverter;
import com.finartz.restaurantApi.model.dto.MealDto;
import com.finartz.restaurantApi.model.request.CreateMealRequest;
import com.finartz.restaurantApi.service.aggregation.MealService;
import com.finartz.restaurantApi.service.foundation.MealFoundationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class MealServiceImpl implements MealService {

    private final MealFoundationService mealFoundationService;
    private final MealRequestConverter mealRequestConverter;

    @Override
    public MealDto saveMeal(CreateMealRequest mealRequest) {
        MealDto mealDto = mealRequestConverter.convertToDto(mealRequest);
        return mealFoundationService.saveMeal(mealDto);
    }

    @Override
    public MealDto getMeal(Long mealId) {

        return mealFoundationService.getMeal(mealId);
    }

    @Override
    public List<MealDto> getByMealName(String mealName) {
        return mealFoundationService.getByName(mealName);
    }


}
