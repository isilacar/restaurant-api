package com.finartz.restaurantApi.service.aggregation;

import com.finartz.restaurantApi.model.dto.CityDto;

import java.util.List;

public interface CityAggregationService {

    List<CityDto> getAllCities();

    CityDto getById(Long id);
}
