package com.finartz.restaurantApi.service.foundation.impl;

import com.finartz.restaurantApi.dao.CityRepository;
import com.finartz.restaurantApi.model.entity.CityEntity;
import com.finartz.restaurantApi.service.foundation.CityFoundationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityFoundationServiceImpl implements CityFoundationService {

    private final CityRepository cityRepository;

    public CityFoundationServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<CityEntity> getAll() {
        return cityRepository.getAll();
    }

    @Override
    public CityEntity getById(Long id) {
        return cityRepository.findById(id);
    }
}
