package com.finartz.restaurantApi.service.foundation;

import com.finartz.restaurantApi.model.entity.CountyEntity;

import java.util.List;

public interface CountyFoundationService {
    List<CountyEntity> getAllByCityId(Long cityId);

    CountyEntity getById(Long id);
}
