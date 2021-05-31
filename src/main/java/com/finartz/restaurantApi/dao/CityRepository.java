package com.finartz.restaurantApi.dao;


import com.finartz.restaurantApi.model.entity.CityEntity;

import java.util.List;

public interface CityRepository {

    List<CityEntity> getAll();

    CityEntity findById(Long id);


}
