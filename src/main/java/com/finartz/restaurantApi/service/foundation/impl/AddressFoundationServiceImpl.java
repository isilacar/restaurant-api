package com.finartz.restaurantApi.service.foundation.impl;

import com.finartz.restaurantApi.dao.AddressRepository;
import com.finartz.restaurantApi.model.converter.dto.impl.*;
import com.finartz.restaurantApi.model.dto.RestaurantAddressDto;
import com.finartz.restaurantApi.model.dto.UserAddressDto;
import com.finartz.restaurantApi.model.entity.*;
import com.finartz.restaurantApi.service.foundation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AddressFoundationServiceImpl implements AddressFoundationService {

    private final AddressRepository addressRepository;
    private final UserFoundationService userFoundationService;
    private final CityFoundationService cityFoundationService;
    private final CountyFoundationService countyFoundationService;
    private final RestaurantFoundationService restaurantFoundationService;
    private final AddressDTOConverter addressDTOConverter;
    private final UserDTOConverter userDTOConverter;
    private final CityDTOConverter cityDTOConverter;
    private final CountyDTOConverter countyDTOConverter;
    private final UserAddressDtoConverter userAddressConverter;
    private final RestaurantAddressDtoConverter restaurantAddressDtoConverter;
    private final RestaurantDTOConverter restaurantDTOConverter;

    @Transactional
    @Override
    public UserAddressDto saveUserAddress(UserAddressDto addressDto) {

        Long userId = addressDto.getUserId();
        Long cityId = addressDto.getCity().getId();
        Long countyId = addressDto.getCounty().getId();

        UserEntity userEntity = userDTOConverter.convertToEntity(userFoundationService.getUser(userId));
        CityEntity cityEntity = cityDTOConverter.convertToEntity(cityFoundationService.getCity(cityId));
        CountyEntity countyEntity = countyDTOConverter.convertToEntity(countyFoundationService.getCounty(countyId));

        AddressEntity addressEntity = addressDTOConverter.convertToEntity(addressDto);
        addressEntity.setUserEntity(userEntity);
        addressEntity.setCityEntity(cityEntity);
        addressEntity.setCountyEntity(countyEntity);
        addressRepository.saveAddress(addressEntity);

        return userAddressConverter.convertToDto(addressEntity);
    }

    @Transactional
    @Override
    public RestaurantAddressDto saveRestaurantAddress(RestaurantAddressDto restaurantAddressDto) {
        Long restaurantId = restaurantAddressDto.getRestaurantId();
        Long cityId = restaurantAddressDto.getCity().getId();
        Long countyId = restaurantAddressDto.getCounty().getId();

        CityEntity cityEntity = cityDTOConverter.convertToEntity(cityFoundationService.getCity(cityId));
        CountyEntity countyEntity = countyDTOConverter.convertToEntity(countyFoundationService.getCounty(countyId));
        RestaurantEntity restaurantEntity = restaurantDTOConverter.convertToEntity(restaurantFoundationService.getRestaurant(restaurantId));


        AddressEntity addressEntity=addressDTOConverter.convertToEntity(restaurantAddressDto);
        addressEntity.setRestaurantEntity(restaurantEntity);
        addressEntity.setCityEntity(cityEntity);
        addressEntity.setCountyEntity(countyEntity);
        addressRepository.saveAddress(addressEntity);

        return restaurantAddressDtoConverter.convertToDto(addressEntity);
    }
}
