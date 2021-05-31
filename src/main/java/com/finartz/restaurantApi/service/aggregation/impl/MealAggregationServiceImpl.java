package com.finartz.restaurantApi.service.aggregation.impl;

import com.finartz.restaurantApi.model.converter.dto.impl.MealDTOConverter;
import com.finartz.restaurantApi.model.dto.MealDto;
import com.finartz.restaurantApi.model.request.CreateMealRequest;
import com.finartz.restaurantApi.service.aggregation.MealAggregationService;
import com.finartz.restaurantApi.service.foundation.MealFoundationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealAggregationServiceImpl implements MealAggregationService {

    private final MealDTOConverter mealConverter;
    private final MealFoundationService mealFoundationService;

    public MealAggregationServiceImpl(MealDTOConverter mealConverter,
                                      MealFoundationService mealFoundationService) {
        this.mealConverter = mealConverter;
        this.mealFoundationService = mealFoundationService;
    }

    @Override
    public MealDto saveMeal(CreateMealRequest mealRequest) {
        return mealConverter.convertToDto(mealFoundationService.saveMeal(mealRequest));
    }

    @Override
    public MealDto getById(Long mealId) {
        return mealConverter.convertToDto(mealFoundationService.getById(mealId));
    }

    @Override
    public List<MealDto> getByMealName(String mealName) {
        return mealConverter.convertToDtoList(mealFoundationService.getByName(mealName));
    }


}
