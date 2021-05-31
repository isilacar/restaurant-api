package com.finartz.restaurantApi.service.aggregation;

import com.finartz.restaurantApi.model.dto.CountyDto;

import java.util.List;

public interface CountyAggregationService {

    List<CountyDto> getAllByCityId(Long cityId);

    CountyDto getById(Long id);

}
