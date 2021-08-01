package com.finartz.restaurantApi.service.foundation.impl;

import com.finartz.restaurantApi.dao.MealRepository;
import com.finartz.restaurantApi.error.MealError;
import com.finartz.restaurantApi.exception.ResourceNotFoundException;
import com.finartz.restaurantApi.model.converter.dto.impl.MealDTOConverter;
import com.finartz.restaurantApi.model.dto.MealDto;
import com.finartz.restaurantApi.model.entity.MealEntity;
import com.finartz.restaurantApi.service.foundation.MealFoundationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealFoundationServiceImpl implements MealFoundationService {

    private final MealRepository mealRepository;
    private final MealDTOConverter dtoConverter;

    @Transactional
    @Override
    public MealDto saveMeal(MealDto mealDto) {

        MealEntity mealEntity = mealRepository.saveMeal(dtoConverter.convertToEntity(mealDto));

        return dtoConverter.convertToDto(mealEntity);
    }

    @Override
    public MealDto getMeal(Long id) {
        MealEntity mealEntity = mealRepository.findMeal(id).orElseThrow(() -> new ResourceNotFoundException(MealError.MEAL_NOT_FOUND));
        return dtoConverter.convertToDto(mealEntity);

    }

    @Override
    public List<MealDto> getByName(String name) {
        List<MealEntity> mealEntities = mealRepository.findByName(name);
        return dtoConverter.convertToDtoList(mealEntities);
    }
}
