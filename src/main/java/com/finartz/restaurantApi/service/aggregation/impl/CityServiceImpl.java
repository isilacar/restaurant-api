package com.finartz.restaurantApi.service.aggregation.impl;

import com.finartz.restaurantApi.model.dto.CityDto;
import com.finartz.restaurantApi.service.aggregation.CityService;
import com.finartz.restaurantApi.service.foundation.CityFoundationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class CityServiceImpl implements CityService {

    private final CityFoundationService cityFoundationService;

    @Override
    public List<CityDto> getCities() {
        return cityFoundationService.getAll();
    }

    @Override
    public CityDto getCity(Long id) {
        return cityFoundationService.getCity(id);
    }


}
