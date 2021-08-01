package com.finartz.restaurantApi.service.foundation;

import com.finartz.restaurantApi.model.dto.RestaurantAddressDto;
import com.finartz.restaurantApi.model.dto.UserAddressDto;

public interface AddressFoundationService {

    UserAddressDto saveUserAddress(UserAddressDto addressDto);
    RestaurantAddressDto saveRestaurantAddress(RestaurantAddressDto restaurantAddressRequest);

}
