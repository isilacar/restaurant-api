package com.finartz.restaurantApi.service.foundation;

import com.finartz.restaurantApi.model.dto.CityDto;

import java.util.List;

public interface CityFoundationService {

    List<CityDto> getAll();

    CityDto getCity(Long id);
}
