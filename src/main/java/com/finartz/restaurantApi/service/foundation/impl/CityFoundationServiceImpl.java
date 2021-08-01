package com.finartz.restaurantApi.service.foundation.impl;

import com.finartz.restaurantApi.dao.CityRepository;
import com.finartz.restaurantApi.model.converter.dto.impl.CityDTOConverter;
import com.finartz.restaurantApi.model.dto.CityDto;
import com.finartz.restaurantApi.model.entity.CityEntity;
import com.finartz.restaurantApi.service.foundation.CityFoundationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityFoundationServiceImpl implements CityFoundationService {

    private final CityRepository cityRepository;
    private final CityDTOConverter cityDTOConverter;

    @Override
    public List<CityDto> getAll() {
        List<CityEntity> cities = cityRepository.findCitites();
        return cityDTOConverter.convertToDtoList(cities);
    }

    @Override
    public CityDto getCity(Long id) {
        CityEntity city = cityRepository.findCity(id);
        return cityDTOConverter.convertToDto(city);
    }
}
