package com.finartz.restaurantApi.dao;

import com.finartz.restaurantApi.model.entity.MealEntity;

import java.util.List;
import java.util.Optional;

public interface MealRepository {

    MealEntity saveMeal(MealEntity mealEntity);

    Optional<MealEntity> findMeal(Long mealId);

    List<MealEntity> findByName(String name);


}
