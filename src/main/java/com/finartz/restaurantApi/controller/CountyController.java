package com.finartz.restaurantApi.controller;

import com.finartz.restaurantApi.controller.endpoint.CountyServiceEndpoint;
import com.finartz.restaurantApi.model.dto.CountyDto;
import com.finartz.restaurantApi.service.aggregation.CountyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class CountyController {

    private final CountyService countyAggregationService;

    @GetMapping(CountyServiceEndpoint.GetCounties.URI)
    public ResponseEntity<Collection<CountyDto>> getCounties(@PathVariable(value = CountyServiceEndpoint.GetCounties.PathVariable.ID) Long cityId) {
        return ResponseEntity.ok().body(countyAggregationService.getAllByCityId(cityId));
    }

}
