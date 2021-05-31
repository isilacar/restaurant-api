package com.finartz.restaurantApi.service.foundation.impl;

import com.finartz.restaurantApi.dao.MealRepository;
import com.finartz.restaurantApi.model.converter.request.impl.MealRequestConverter;
import com.finartz.restaurantApi.model.entity.MealEntity;
import com.finartz.restaurantApi.model.request.CreateMealRequest;
import com.finartz.restaurantApi.service.foundation.MealFoundationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealFoundationServiceImpl implements MealFoundationService {

    private final MealRepository mealRepository;
    private final MealRequestConverter mealRequestConverter;

    public MealFoundationServiceImpl(MealRepository mealRepository,
                                     MealRequestConverter mealRequestConverter) {
        this.mealRepository = mealRepository;
        this.mealRequestConverter = mealRequestConverter;
    }
    @Override
    public MealEntity saveMeal(CreateMealRequest mealRequest) {
        return mealRepository.saveMeal(mealRequestConverter.convertToEntity(mealRequest));
    }

    @Override
    public MealEntity getById(Long id) {

        return mealRepository.findById(id);
    }

    @Override
    public List<MealEntity> getByName(String name) {
        return mealRepository.findByName(name);
    }
}
