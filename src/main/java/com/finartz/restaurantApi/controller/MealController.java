package com.finartz.restaurantApi.controller;


import com.finartz.restaurantApi.controller.endpoint.MealServiceEndpoint;
import com.finartz.restaurantApi.model.dto.MealDto;
import com.finartz.restaurantApi.model.request.CreateMealRequest;
import com.finartz.restaurantApi.service.aggregation.MealAggregationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
public class MealController {

    private final MealAggregationService mealAggregationService;

    public MealController(MealAggregationService mealAggregationService) {
        this.mealAggregationService = mealAggregationService;
    }

    @PostMapping(MealServiceEndpoint.GetBaseUri.URI)
    public ResponseEntity<MealDto> saveMeal(@Valid @RequestBody CreateMealRequest mealRequest) {
        return ResponseEntity.ok().body(mealAggregationService.saveMeal(mealRequest));
    }

    @GetMapping(MealServiceEndpoint.GetById.URI)
    public ResponseEntity<MealDto> getById(@PathVariable(MealServiceEndpoint.GetById.PathVariable.ID) Long mealId) {
        return ResponseEntity.ok().body(mealAggregationService.getById(mealId));
    }

    @GetMapping(MealServiceEndpoint.GetByMealName.URI)
    public ResponseEntity<Collection<MealDto>> getByMealName(@PathVariable(MealServiceEndpoint.GetByMealName.PathVariable.MEAL_NAME) String mealName) {
        return ResponseEntity.ok().body(mealAggregationService.getByMealName(mealName));
    }

}
