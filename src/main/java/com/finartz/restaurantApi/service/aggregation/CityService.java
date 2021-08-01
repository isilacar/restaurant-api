package com.finartz.restaurantApi.service.aggregation;

import com.finartz.restaurantApi.model.dto.CityDto;

import java.util.List;

public interface CityService {

    List<CityDto> getCities();

    CityDto getCity(Long id);
}
