package com.finartz.restaurantApi.service.aggregation.impl;

import com.finartz.restaurantApi.model.converter.dto.impl.CountyDTOConverter;
import com.finartz.restaurantApi.model.dto.CountyDto;
import com.finartz.restaurantApi.service.aggregation.CountyAggregationService;
import com.finartz.restaurantApi.service.foundation.CountyFoundationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountyAggregationServiceImpl implements CountyAggregationService {

    private final CountyDTOConverter countyConverter;
    private final CountyFoundationService countyFoundationService;

    public CountyAggregationServiceImpl(CountyDTOConverter countyConverter,
                                        CountyFoundationService countyFoundationService) {
        this.countyConverter = countyConverter;
        this.countyFoundationService = countyFoundationService;
    }

    @Override
    public List<CountyDto> getAllByCityId(Long cityId) {
        return countyConverter.convertToDtoList(countyFoundationService.getAllByCityId(cityId));
    }

    @Override
    public CountyDto getById(Long id) {
        return countyConverter.convertToDto(countyFoundationService.getById(id));
    }
}
