package com.finartz.restaurantApi.service.foundation;

import com.finartz.restaurantApi.model.entity.CityEntity;

import java.util.List;

public interface CityFoundationService {

    List<CityEntity> getAll();

    CityEntity getById(Long id);
}
