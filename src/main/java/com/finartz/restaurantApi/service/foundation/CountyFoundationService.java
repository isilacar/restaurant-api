package com.finartz.restaurantApi.service.foundation;

import com.finartz.restaurantApi.model.dto.CountyDto;

import java.util.List;

public interface CountyFoundationService {
    List<CountyDto> getByCity(Long cityId);

    CountyDto getCounty(Long id);
}
