package com.finartz.restaurantApi.model.converter.request.impl;

import com.finartz.restaurantApi.model.dto.AddressDto;
import com.finartz.restaurantApi.model.dto.CityDto;
import com.finartz.restaurantApi.model.dto.CountyDto;
import com.finartz.restaurantApi.model.dto.UserDto;
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


    public AddressDto convertToDto(CreateRestaurantAddressRequest createRestaurantAddressRequest) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(createRestaurantAddressRequest.getId());
        addressDto.setCreateDate(createRestaurantAddressRequest.getCreateDate());
        addressDto.setUpdateDate(createRestaurantAddressRequest.getUpdateDate());
        addressDto.setDistrictId(createRestaurantAddressRequest.getDistrictId());
        addressDto.setZipCode(createRestaurantAddressRequest.getZipCode());
        addressDto.setDetail(createRestaurantAddressRequest.getDetail());
        addressDto.setTitle(createRestaurantAddressRequest.getTitle());
        return addressDto;
    }


    public AddressDto convertToDto(CreateUserAddressRequest createUserAddressRequest) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(createUserAddressRequest.getId());
        addressDto.setCreateDate(createUserAddressRequest.getCreateDate());
        addressDto.setUpdateDate(createUserAddressRequest.getUpdateDate());
        addressDto.setDistrictId(createUserAddressRequest.getDistrictId());
        addressDto.setZipCode(createUserAddressRequest.getZipCode());
        addressDto.setDetail(createUserAddressRequest.getDetail());
        addressDto.setTitle(createUserAddressRequest.getTitle());
        addressDto.setCounty(getCountyDto(createUserAddressRequest));
        addressDto.setCity(getCityDto(createUserAddressRequest));
        return addressDto;
    }

    public UserDto convertUserDto(CreateUserAddressRequest userAddressRequest){
        UserDto userDto=new UserDto();
        userDto.setId(userAddressRequest.getUserId());
        return userDto;
    }
    public CountyDto getCountyDto(CreateUserAddressRequest userAddressRequest){
        CountyDto countyDto=new CountyDto();
        countyDto.setId(userAddressRequest.getCountyId());
        return countyDto;
    }
    public CityDto getCityDto(CreateUserAddressRequest userAddressRequest){
        CityDto cityDto=new CityDto();
        cityDto.setId(userAddressRequest.getCityId());
        return cityDto;
    }

}
