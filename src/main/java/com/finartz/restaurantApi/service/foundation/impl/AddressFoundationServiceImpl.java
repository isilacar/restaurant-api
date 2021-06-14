package com.finartz.restaurantApi.service.foundation.impl;

import com.finartz.restaurantApi.dao.AddressRepository;
import com.finartz.restaurantApi.model.converter.request.impl.AddressRequestConverter;
import com.finartz.restaurantApi.model.entity.*;
import com.finartz.restaurantApi.model.request.CreateRestaurantAddressRequest;
import com.finartz.restaurantApi.model.request.CreateUserAddressRequest;
import com.finartz.restaurantApi.service.foundation.*;
import org.springframework.stereotype.Service;

@Service
public class AddressFoundationServiceImpl implements AddressFoundationService {

    private final AddressRepository addressRepository;
    private final UserFoundationService userFoundationService;
    private final CityFoundationService cityFoundationService;
    private final CountyFoundationService countyFoundationService;
    private final AddressRequestConverter addressRequestConverter;
    private final RestaurantFoundationService restaurantFoundationService;

    public AddressFoundationServiceImpl(AddressRepository addressRepository,
                                        UserFoundationService userFoundationService,
                                        CityFoundationService cityFoundationService,
                                        CountyFoundationService countyFoundationService,
                                        AddressRequestConverter addressRequestConverter, RestaurantFoundationService restaurantFoundationService) {
        this.addressRepository = addressRepository;
        this.userFoundationService = userFoundationService;
        this.cityFoundationService = cityFoundationService;
        this.countyFoundationService = countyFoundationService;
        this.addressRequestConverter = addressRequestConverter;
        this.restaurantFoundationService = restaurantFoundationService;
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
        RestaurantEntity restaurantEntity = restaurantFoundationService.getById(restaurantAddressRequest.getRestaurantId());
        CityEntity cityEntity = cityFoundationService.getById(restaurantAddressRequest.getCityId());
        CountyEntity countyEntity = countyFoundationService.getById(restaurantAddressRequest.getCountyId());
        AddressEntity addressEntity = addressRequestConverter.convert(restaurantAddressRequest);
        addressEntity.setRestaurantEntity(restaurantEntity);
        addressEntity.setCityEntity(cityEntity);
        addressEntity.setCountyEntity(countyEntity);

        return addressRepository.saveAddress(addressEntity);
    }
}
