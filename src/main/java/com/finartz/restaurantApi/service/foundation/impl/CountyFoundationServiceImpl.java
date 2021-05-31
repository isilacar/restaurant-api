package com.finartz.restaurantApi.service.foundation.impl;

import com.finartz.restaurantApi.dao.CountyRepository;
import com.finartz.restaurantApi.model.entity.CountyEntity;
import com.finartz.restaurantApi.service.foundation.CountyFoundationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountyFoundationServiceImpl implements CountyFoundationService {

    private final CountyRepository countyRepository;

    public CountyFoundationServiceImpl(CountyRepository countyRepository) {
        this.countyRepository = countyRepository;
    }

    @Override
    public List<CountyEntity> getAllByCityId(Long cityId) {

        return countyRepository.getAllCountiesByCityId(cityId);
    }

    @Override
    public CountyEntity getById(Long id) {
        return countyRepository.findById(id);
    }
}
