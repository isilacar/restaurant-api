package com.finartz.restaurantApi.controller;


import com.finartz.restaurantApi.controller.endpoint.CityServiceEndpoint;
import com.finartz.restaurantApi.model.dto.CityDto;
import com.finartz.restaurantApi.service.aggregation.CityAggregationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {

    private final CityAggregationService cityAggregationService;

    public CityController(CityAggregationService cityService) {
        this.cityAggregationService = cityService;
    }

    @GetMapping(CityServiceEndpoint.GetAll.URI)
    public ResponseEntity<List<CityDto>> getAll() {
        return ResponseEntity.ok().body(cityAggregationService.getAllCities());
    }


}
