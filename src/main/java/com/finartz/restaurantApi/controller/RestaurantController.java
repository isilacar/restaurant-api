package com.finartz.restaurantApi.controller;


import com.finartz.restaurantApi.controller.endpoint.RestaurantServiceEndpoint;
import com.finartz.restaurantApi.model.dto.RestaurantDto;
import com.finartz.restaurantApi.model.enumeration.RestaurantStatus;
import com.finartz.restaurantApi.model.request.CreateRestaurantRequest;
import com.finartz.restaurantApi.service.aggregation.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping(RestaurantServiceEndpoint.Save.URI)
    public ResponseEntity<RestaurantDto> save(@Valid @RequestBody CreateRestaurantRequest createRestaurantRequest) {
        RestaurantDto restaurantDto = restaurantService.saveRestaurant(createRestaurantRequest);
        return ResponseEntity.ok().body(restaurantDto);
    }

    @GetMapping(RestaurantServiceEndpoint.GetById.URI)
    public ResponseEntity<RestaurantDto> getById(@PathVariable(value = RestaurantServiceEndpoint.GetById.PathVariable.ID)
                                                         Long id) {
        return ResponseEntity.ok().body(restaurantService.getById(id));
    }

    @GetMapping(RestaurantServiceEndpoint.GetRestaurants.URI)
    public ResponseEntity<List<RestaurantDto>> getRestaurants(@RequestParam(required = false) RestaurantStatus status ) {
        return ResponseEntity.ok().body(restaurantService.getRestaurants(status));
    }

    @PutMapping(RestaurantServiceEndpoint.UpdateRestaurant.URI)
    public ResponseEntity<?> updateRestaurant(@PathVariable(value = RestaurantServiceEndpoint.UpdateRestaurant.PathVariable.ID)
                                                      Long id,
                                              @RequestBody CreateRestaurantRequest createRestaurantRequest) {
        restaurantService.updateRestaurant(id, createRestaurantRequest);
        return ResponseEntity.ok().build();
    }

}











    /*
    @GetMapping(RestaurantServiceEndpoint.GetAllNearestRestaurantByStatusApproved.URI)
    public ResponseEntity<List<RestaurantDto>> getAllNearestRestaurantByStatusApproved() {
        return ResponseEntity.ok().body(restaurantService.getAllNearestRestaurantByApproved());
    }


    @GetMapping(RestaurantServiceEndpoint.GetAllByStatusAwaiting.URI)
    public ResponseEntity<List<RestaurantDto>> getAllByStatusAwaiting() {
        return ResponseEntity.ok().body(restaurantService.getAllRestaurantByAwaiting());
    }

    @GetMapping(RestaurantServiceEndpoint.GetAllByStatusApproved.URI)
    public ResponseEntity<List<RestaurantDto>> getAllByStatusApproved() {
        return ResponseEntity.ok().body(restaurantService.getAllRestaurantByApproved());
    }


     */


