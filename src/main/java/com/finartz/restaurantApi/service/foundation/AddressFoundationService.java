package com.finartz.restaurantApi.service.foundation;

import com.finartz.restaurantApi.model.entity.AddressEntity;
import com.finartz.restaurantApi.model.request.CreateRestaurantAddressRequest;
import com.finartz.restaurantApi.model.request.CreateUserAddressRequest;

public interface AddressFoundationService {

    AddressEntity saveUserAddress(CreateUserAddressRequest userAddressRequest);
    AddressEntity saveRestaurantAddress(CreateRestaurantAddressRequest restaurantAddressRequest);

}
