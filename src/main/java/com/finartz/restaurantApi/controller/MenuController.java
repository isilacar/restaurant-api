package com.finartz.restaurantApi.controller;

import com.finartz.restaurantApi.controller.endpoint.MenuServiceEndpoint;
import com.finartz.restaurantApi.model.dto.MenuDto;
import com.finartz.restaurantApi.model.dto.RestaurantMenuDto;
import com.finartz.restaurantApi.model.request.CreateRestaurantMenuRequest;
import com.finartz.restaurantApi.model.request.MealMenuRequest;
import com.finartz.restaurantApi.service.aggregation.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping(MenuServiceEndpoint.SaveRestaurantMenu.URI)
    public ResponseEntity<RestaurantMenuDto> saveRestaurantMenu(@Valid @RequestBody CreateRestaurantMenuRequest menuRequest) {
        RestaurantMenuDto restaurantMenuDto = menuService.saveRestaurantMenu(menuRequest);
        return ResponseEntity.ok().body(restaurantMenuDto);

    }

    @GetMapping(MenuServiceEndpoint.GetById.URI)
    public ResponseEntity<MenuDto> getById(@PathVariable(value = MenuServiceEndpoint.GetById.PathVariable.ID)
                                                       Long menuId){
        return ResponseEntity.ok().body(menuService.getById(menuId));
    }

    @PostMapping(MenuServiceEndpoint.SaveMeal.URI)
    public ResponseEntity<?> saveMeal(@Valid @RequestBody MealMenuRequest mealMenuRequest){
        menuService.saveMeal(mealMenuRequest);
        return ResponseEntity.ok().build();
    }

}
