package com.finartz.restaurantApi.controller;

import com.finartz.restaurantApi.controller.endpoint.CountyServiceEndpoint;
import com.finartz.restaurantApi.model.dto.CountyDto;
import com.finartz.restaurantApi.service.aggregation.CountyAggregationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class CountyController {

    private final CountyAggregationService countyAggregationService;

    public CountyController(CountyAggregationService countyAggregationService) {
        this.countyAggregationService = countyAggregationService;
    }

    @GetMapping(CountyServiceEndpoint.GetCounties.URI)
    public ResponseEntity<Collection<CountyDto>> getCounties(@PathVariable(value = CountyServiceEndpoint.GetCounties.PathVariable.ID) Long cityId) {
        return ResponseEntity.ok().body(countyAggregationService.getAllByCityId(cityId));
    }

}
