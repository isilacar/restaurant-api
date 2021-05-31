package com.finartz.restaurantApi.service.aggregation.impl;

import com.finartz.restaurantApi.model.converter.dto.impl.CityDTOConverter;
import com.finartz.restaurantApi.model.dto.CityDto;
import com.finartz.restaurantApi.service.aggregation.CityAggregationService;
import com.finartz.restaurantApi.service.foundation.CityFoundationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityAggregationServiceImpl implements CityAggregationService {

    private final CityDTOConverter cityConverter;
    private final CityFoundationService cityFoundationService;

    public CityAggregationServiceImpl(CityDTOConverter cityConverter,
                                      CityFoundationService cityFoundationService) {
        this.cityConverter = cityConverter;
        this.cityFoundationService = cityFoundationService;
    }

    @Override
    public List<CityDto> getAllCities() {
        return cityConverter.convertToDtoList(cityFoundationService.getAll());
    }

    @Override
    public CityDto getById(Long id) {
        return cityConverter.convertToDto(cityFoundationService.getById(id));
    }


}
