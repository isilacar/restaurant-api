package com.finartz.restaurantApi.service.aggregation.impl;

import com.finartz.restaurantApi.model.converter.dto.impl.RestaurantAddressDtoConverter;
import com.finartz.restaurantApi.model.converter.dto.impl.UserAddressDtoConverter;
import com.finartz.restaurantApi.model.dto.RestaurantAddressDto;
import com.finartz.restaurantApi.model.dto.UserAddressDto;
import com.finartz.restaurantApi.model.request.CreateRestaurantAddressRequest;
import com.finartz.restaurantApi.model.request.CreateUserAddressRequest;
import com.finartz.restaurantApi.service.aggregation.AddressService;
import com.finartz.restaurantApi.service.foundation.AddressFoundationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class AddressServiceImpl implements AddressService {

    private final AddressFoundationService addressFoundationService;
    private final UserAddressDtoConverter userAddressConverter;
    private final RestaurantAddressDtoConverter restaurantAddressConverter;

    @Override
    public UserAddressDto saveUserAddress(CreateUserAddressRequest userAddressRequest) {
        UserAddressDto addressDto = userAddressConverter.convert(userAddressRequest);
        return addressFoundationService.saveUserAddress(addressDto);

    }

    @Override
    public RestaurantAddressDto saveRestaurantAddress(CreateRestaurantAddressRequest restaurantAddressRequest) {//restaurantAddressDto
        RestaurantAddressDto restaurantAddressDto = restaurantAddressConverter.convert(restaurantAddressRequest);
        return addressFoundationService.saveRestaurantAddress(restaurantAddressDto);
    }


}
