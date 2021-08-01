package com.finartz.restaurantApi.dao;


import com.finartz.restaurantApi.model.entity.CityEntity;

import java.util.List;

public interface CityRepository {

    List<CityEntity> findCitites();

    CityEntity findCity(Long id);


}
