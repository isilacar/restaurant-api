package com.finartz.restaurantApi.model.converter.request.impl;

import com.finartz.restaurantApi.model.entity.AddressEntity;
import com.finartz.restaurantApi.model.request.CreateRestaurantAddressRequest;
import com.finartz.restaurantApi.model.request.CreateUserAddressRequest;
import org.springframework.stereotype.Component;

@Component
public class AddressRequestConverter {

    public AddressEntity convert(CreateRestaurantAddressRequest createRestaurantAddressRequest) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(createRestaurantAddressRequest.getId());
        addressEntity.setCreateDate(createRestaurantAddressRequest.getCreateDate());
        addressEntity.setUpdateDate(createRestaurantAddressRequest.getUpdateDate());
        addressEntity.setDistrictId(createRestaurantAddressRequest.getDistrictId());
        addressEntity.setZipCode(createRestaurantAddressRequest.getZipCode());
        addressEntity.setDetail(createRestaurantAddressRequest.getDetail());
        addressEntity.setTitle(createRestaurantAddressRequest.getTitle());
        return addressEntity;
    }

    public AddressEntity convert(CreateUserAddressRequest createUserAddressRequest) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(createUserAddressRequest.getId());
        addressEntity.setCreateDate(createUserAddressRequest.getCreateDate());
        addressEntity.setUpdateDate(createUserAddressRequest.getUpdateDate());
        addressEntity.setDistrictId(createUserAddressRequest.getDistrictId());
        addressEntity.setZipCode(createUserAddressRequest.getZipCode());
        addressEntity.setDetail(createUserAddressRequest.getDetail());
        addressEntity.setTitle(createUserAddressRequest.getTitle());
        return addressEntity;
    }

}
