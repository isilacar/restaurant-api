package com.finartz.restaurantApi.dao;

import com.finartz.restaurantApi.model.entity.CountyEntity;

import java.util.List;

public interface CountyRepository{

    List<CountyEntity> findCounties(Long cityId);

    CountyEntity findCounty(Long id);
}
