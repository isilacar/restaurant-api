package com.finartz.restaurantApi.service.aggregation.impl;

import com.finartz.restaurantApi.model.dto.CountyDto;
import com.finartz.restaurantApi.service.aggregation.CountyService;
import com.finartz.restaurantApi.service.foundation.CountyFoundationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class CountyServiceImpl implements CountyService {

    private final CountyFoundationService countyFoundationService;

    @Override
    public List<CountyDto> getAllByCityId(Long cityId) {
        return countyFoundationService.getByCity(cityId);
    }

    @Override
    public CountyDto getById(Long id) {
        return countyFoundationService.getCounty(id);
    }
}
