package com.finartz.restaurantApi.service.aggregation.impl;

import com.finartz.restaurantApi.model.converter.dto.impl.AddressDTOConverter;
import com.finartz.restaurantApi.model.dto.AddressDto;
import com.finartz.restaurantApi.model.request.CreateRestaurantAddressRequest;
import com.finartz.restaurantApi.model.request.CreateUserAddressRequest;
import com.finartz.restaurantApi.service.aggregation.AddressAggregationService;
import com.finartz.restaurantApi.service.foundation.AddressFoundationService;
import org.springframework.stereotype.Service;

@Service
public class AddressAggregationServiceImpl implements AddressAggregationService {

    private final AddressDTOConverter addressConverter;
    private final AddressFoundationService addressFoundationService;

    public AddressAggregationServiceImpl(AddressDTOConverter addressConverter,
                                         AddressFoundationService addressFoundationService) {
        this.addressConverter = addressConverter;
        this.addressFoundationService = addressFoundationService;
    }

    @Override
    public AddressDto saveUserAddress(CreateUserAddressRequest userAddressRequest) {
        return addressConverter.convertToDto(addressFoundationService.saveUserAddress(userAddressRequest));
    }

    @Override
    public AddressDto saveRestaurantAddress(CreateRestaurantAddressRequest restaurantAddressRequest) {
        return addressConverter.convertToDto(addressFoundationService.saveRestaurantAddress(restaurantAddressRequest));
    }


}
