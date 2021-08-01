package com.finartz.restaurantApi.controller;

import com.finartz.restaurantApi.controller.endpoint.AddressServiceEndpoint;
import com.finartz.restaurantApi.model.dto.RestaurantAddressDto;
import com.finartz.restaurantApi.model.dto.UserAddressDto;
import com.finartz.restaurantApi.model.request.CreateRestaurantAddressRequest;
import com.finartz.restaurantApi.model.request.CreateUserAddressRequest;
import com.finartz.restaurantApi.service.aggregation.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping(AddressServiceEndpoint.SaveUserAddress.URI)
    public ResponseEntity<UserAddressDto> saveUserAddress(@Valid @RequestBody CreateUserAddressRequest userAddressRequest) {

        return ResponseEntity.ok().body(addressService.saveUserAddress(userAddressRequest));
    }

    @PostMapping(AddressServiceEndpoint.SaveRestaurantAddress.URI)
    public ResponseEntity<RestaurantAddressDto> saveRestaurantAddress(@Valid @RequestBody CreateRestaurantAddressRequest restaurantAddressRequest) {
        return ResponseEntity.ok().body(addressService.saveRestaurantAddress(restaurantAddressRequest));
    }


}
