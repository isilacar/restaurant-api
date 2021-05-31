package com.finartz.restaurantApi.service.foundation.impl;

import com.finartz.restaurantApi.dao.AddressRepository;
import com.finartz.restaurantApi.model.converter.request.impl.AddressRequestConverter;
import com.finartz.restaurantApi.model.entity.AddressEntity;
import com.finartz.restaurantApi.model.entity.CityEntity;
import com.finartz.restaurantApi.model.entity.CountyEntity;
import com.finartz.restaurantApi.model.entity.UserEntity;
import com.finartz.restaurantApi.model.request.CreateRestaurantAddressRequest;
import com.finartz.restaurantApi.model.request.CreateUserAddressRequest;
import com.finartz.restaurantApi.service.foundation.AddressFoundationService;
import com.finartz.restaurantApi.service.foundation.CityFoundationService;
import com.finartz.restaurantApi.service.foundation.CountyFoundationService;
import com.finartz.restaurantApi.service.foundation.UserFoundationService;
import org.springframework.stereotype.Service;

@Service
public class AddressFoundationServiceImpl implements AddressFoundationService {

    private final AddressRepository addressRepository;
    private final UserFoundationService userFoundationService;
    private final CityFoundationService cityFoundationService;
    private final CountyFoundationService countyFoundationService;
    private final AddressRequestConverter addressRequestConverter;

    public AddressFoundationServiceImpl(AddressRepository addressRepository,
                                        UserFoundationService userFoundationService,
                                        CityFoundationService cityFoundationService,
                                        CountyFoundationService countyFoundationService,
                                        AddressRequestConverter addressRequestConverter) {
        this.addressRepository = addressRepository;
        this.userFoundationService = userFoundationService;
        this.cityFoundationService = cityFoundationService;
        this.countyFoundationService = countyFoundationService;
        this.addressRequestConverter = addressRequestConverter;
    }

    @Override
    public AddressEntity saveUserAddress(CreateUserAddressRequest userAddressRequest) {

        UserEntity userEntity = userFoundationService.getById(userAddressRequest.getUserId());
        CityEntity cityEntity = cityFoundationService.getById(userAddressRequest.getCityId());
        CountyEntity countyEntity = countyFoundationService.getById(userAddressRequest.getCountyId());
        AddressEntity addressEntity = addressRequestConverter.convert(userAddressRequest);
        addressEntity.setUserEntity(userEntity);
        addressEntity.setCityEntity(cityEntity);
        addressEntity.setCountyEntity(countyEntity);

        return addressRepository.saveAddress(addressEntity);
    }

    @Override
    public AddressEntity saveRestaurantAddress(CreateRestaurantAddressRequest restaurantAddressRequest) {
        UserEntity userEntity = userFoundationService.getById(restaurantAddressRequest.getRestaurantId());
        CityEntity cityEntity = cityFoundationService.getById(restaurantAddressRequest.getCityId());
        CountyEntity countyEntity = countyFoundationService.getById(restaurantAddressRequest.getCountyId());
        AddressEntity addressEntity = addressRequestConverter.convert(restaurantAddressRequest);
        addressEntity.setUserEntity(userEntity);
        addressEntity.setCityEntity(cityEntity);
        addressEntity.setCountyEntity(countyEntity);

        return addressRepository.saveAddress(addressEntity);
    }
}
