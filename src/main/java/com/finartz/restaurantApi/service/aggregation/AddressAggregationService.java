package com.finartz.restaurantApi.service.aggregation;

import com.finartz.restaurantApi.model.dto.AddressDto;
import com.finartz.restaurantApi.model.request.CreateRestaurantAddressRequest;
import com.finartz.restaurantApi.model.request.CreateUserAddressRequest;

public interface AddressAggregationService {

    AddressDto saveUserAddress(CreateUserAddressRequest userAddressRequest);
    AddressDto saveRestaurantAddress(CreateRestaurantAddressRequest restaurantAddressRequest);

}
