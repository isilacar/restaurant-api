package com.finartz.restaurantApi.dao;

import com.finartz.restaurantApi.model.entity.CountyEntity;

import java.util.List;

public interface CountyRepository{

    List<CountyEntity> getAllCountiesByCityId(Long cityId);

    CountyEntity findById(Long id);
}
