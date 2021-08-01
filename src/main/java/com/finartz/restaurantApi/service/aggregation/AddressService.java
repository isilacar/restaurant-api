package com.finartz.restaurantApi.service.aggregation;

import com.finartz.restaurantApi.model.dto.RestaurantAddressDto;
import com.finartz.restaurantApi.model.dto.UserAddressDto;
import com.finartz.restaurantApi.model.request.CreateRestaurantAddressRequest;
import com.finartz.restaurantApi.model.request.CreateUserAddressRequest;

public interface AddressService {

    UserAddressDto saveUserAddress(CreateUserAddressRequest userAddressRequest);
    RestaurantAddressDto saveRestaurantAddress(CreateRestaurantAddressRequest restaurantAddressRequest);

}
