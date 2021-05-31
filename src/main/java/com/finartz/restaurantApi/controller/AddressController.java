package com.finartz.restaurantApi.controller;

import com.finartz.restaurantApi.controller.endpoint.AddressServiceEndpoint;
import com.finartz.restaurantApi.model.dto.AddressDto;
import com.finartz.restaurantApi.model.request.CreateRestaurantAddressRequest;
import com.finartz.restaurantApi.model.request.CreateUserAddressRequest;
import com.finartz.restaurantApi.service.aggregation.AddressAggregationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AddressController {

    private final AddressAggregationService addressAggregationService;

    public AddressController(AddressAggregationService addressService) {
        this.addressAggregationService = addressService;
    }

    @PostMapping(AddressServiceEndpoint.SaveUserAddress.URI)
    public ResponseEntity<AddressDto> saveUserAddress(@RequestBody CreateUserAddressRequest userAddressRequest) {
        return ResponseEntity.ok().body(addressAggregationService.saveUserAddress(userAddressRequest));
    }

    @PostMapping(AddressServiceEndpoint.SaveRestaurantAddress.URI)
    public ResponseEntity<AddressDto> saveRestaurantAddress(@Valid @RequestBody CreateRestaurantAddressRequest restaurantAddressRequest) {
        return ResponseEntity.ok().body(addressAggregationService.saveRestaurantAddress(restaurantAddressRequest));
    }


}
