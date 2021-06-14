package com.finartz.restaurantApi.controller;

import com.finartz.restaurantApi.controller.endpoint.MenuServiceEndpoint;
import com.finartz.restaurantApi.model.dto.MenuDto;
import com.finartz.restaurantApi.model.request.CreateMealMenuRequest;
import com.finartz.restaurantApi.model.request.CreateRestaurantMenuRequest;
import com.finartz.restaurantApi.service.aggregation.MenuAggregationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class MenuController {

    private final MenuAggregationService menuAggregationService;

    public MenuController(MenuAggregationService menuService) {
        this.menuAggregationService = menuService;
    }

    @PostMapping(MenuServiceEndpoint.SaveRestaurantMenu.URI)
    public ResponseEntity<MenuDto> saveRestaurantMenu(@Valid @RequestBody CreateRestaurantMenuRequest menuRequest) {
        return ResponseEntity.ok().body(menuAggregationService.saveRestaurantMenu(menuRequest));

    }

    @GetMapping(MenuServiceEndpoint.GetById.URI)
    public ResponseEntity<MenuDto> getById(@PathVariable(value = MenuServiceEndpoint.GetById.PathVariable.ID)
                                                       Long menuId){
        return ResponseEntity.ok().body(menuAggregationService.getById(menuId));
    }

    @PostMapping(MenuServiceEndpoint.SaveMeal.URI)
    public ResponseEntity<?> saveMeal(@Valid @RequestBody CreateMealMenuRequest createMealMenuRequest){
        menuAggregationService.saveMeal(createMealMenuRequest);
        return ResponseEntity.ok().build();
    }
}
