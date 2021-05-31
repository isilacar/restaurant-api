package com.finartz.restaurantApi.controller;


import com.finartz.restaurantApi.controller.endpoint.RestaurantServiceEndpoint;
import com.finartz.restaurantApi.model.dto.RestaurantDto;
import com.finartz.restaurantApi.model.enumeration.StatusEnum;
import com.finartz.restaurantApi.model.request.CreateRestaurantRequest;
import com.finartz.restaurantApi.service.aggregation.RestaurantAggregationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController {

    private final RestaurantAggregationService restaurantAggregationService;

    public RestaurantController(RestaurantAggregationService restaurantAggregationService) {
        this.restaurantAggregationService = restaurantAggregationService;
    }

    @PostMapping(RestaurantServiceEndpoint.Save.URI)
    public ResponseEntity<RestaurantDto> save(@RequestBody CreateRestaurantRequest createRestaurantRequest) {
        return ResponseEntity.ok().body(restaurantAggregationService.saveRestaurant(createRestaurantRequest));
    }

    @GetMapping(RestaurantServiceEndpoint.GetById.URI)
    public ResponseEntity<RestaurantDto> getById(@PathVariable(value = RestaurantServiceEndpoint.GetById.PathVariable.ID)
                                                             Long id) {
        return ResponseEntity.ok().body(restaurantAggregationService.getById(id));
    }


    @GetMapping(RestaurantServiceEndpoint.GetAllNearestRestaurantByStatusApproved.URI)
    public ResponseEntity<List<RestaurantDto>> getAllNearestRestaurantByStatusApproved() {
        return ResponseEntity.ok().body(restaurantAggregationService.getAllNearestRestaurantByApproved());
    }

    //requestbody createRestaurant ile restaurantı gönder

    @PutMapping(RestaurantServiceEndpoint.UpdateRestaurant.URI)
    public ResponseEntity<?> updateRestaurant(@PathVariable(value = RestaurantServiceEndpoint.UpdateRestaurant.PathVariable.ID)
                                                          Long id,
                                              @RequestBody CreateRestaurantRequest createRestaurantRequest) {
        restaurantAggregationService.updateRestaurant(id, createRestaurantRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping(RestaurantServiceEndpoint.GetAllByStatus.URI)
    public ResponseEntity<List<RestaurantDto>> getAllByStatus(@RequestParam StatusEnum statusEnum) {
        return ResponseEntity.ok().body(restaurantAggregationService.getAllRestaurantByStatus(statusEnum));
    }

    /*
    @GetMapping("/awaiting")
    public ResponseEntity<List<RestaurantDto>> getAllByStatusAwaiting(){
        return ResponseEntity.ok().body(restaurantAggregationService.getAllRestaurantByAwaiting());
    }


    @GetMapping
    public ResponseEntity<List<RestaurantDto>> getAllByStatusApproved(){
        return ResponseEntity.ok().body( restaurantAggregationService.getAllRestaurantByApproved());
    }

       @PutMapping("/{id}")
    public ResponseEntity<?> updateStatus1(@PathVariable Long id,
                                          @RequestParam("status") StatusEnum status){
        restaurantAggregationService.updateStatus(id,status);
        return ResponseEntity.ok().build();
    }

 */

}
