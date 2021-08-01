package com.finartz.restaurantApi.service.foundation.impl;

import com.finartz.restaurantApi.dao.CountyRepository;
import com.finartz.restaurantApi.model.converter.dto.impl.CountyDTOConverter;
import com.finartz.restaurantApi.model.dto.CountyDto;
import com.finartz.restaurantApi.model.entity.CountyEntity;
import com.finartz.restaurantApi.service.foundation.CountyFoundationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountyFoundationServiceImpl implements CountyFoundationService {

    private final CountyRepository countyRepository;
    private final CountyDTOConverter countyDTOConverter;

    @Override
    public List<CountyDto> getByCity(Long cityId) {

        List<CountyEntity> countyEntities = countyRepository.findCounties(cityId);
        return countyDTOConverter.convertToDtoList(countyEntities);
    }

    @Override
    public CountyDto getCounty(Long id) {
        CountyEntity countyEntity = countyRepository.findCounty(id);
        return countyDTOConverter.convertToDto(countyEntity);
    }
}
