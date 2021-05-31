package com.finartz.restaurantApi.dao;

import com.finartz.restaurantApi.model.entity.MealEntity;

import java.util.List;

public interface MealRepository {

    MealEntity saveMeal(MealEntity mealEntity);

    MealEntity findById(Long mealId);

    List<MealEntity> findByName(String name);


}
