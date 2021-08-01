package com.finartz.restaurantApi.model.converter.dto.impl;

import com.finartz.restaurantApi.model.converter.dto.BaseDTOConverter;
import com.finartz.restaurantApi.model.dto.UserAddressDto;
import com.finartz.restaurantApi.model.entity.AddressEntity;
import com.finartz.restaurantApi.model.request.CreateUserAddressRequest;
import com.finartz.restaurantApi.service.aggregation.CityService;
import com.finartz.restaurantApi.service.aggregation.CountyService;
import com.finartz.restaurantApi.service.aggregation.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAddressDtoConverter implements BaseDTOConverter<AddressEntity, UserAddressDto> {

    private final CityDTOConverter cityConverter;
    private final CountyDTOConverter countyConverter;
    private final UserDTOConverter userConverter;
    private final UserService userService;
    private final CityService cityService;
    private final CountyService countyService;


    @Override
    public AddressEntity convertToEntity(UserAddressDto dto) {

        if (dto == null) {
            return null;
        }
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(dto.getId());
        addressEntity.setCreateDate(dto.getCreateDate());
        addressEntity.setUpdateDate(dto.getUpdateDate());
        if (dto.getCity() != null) {
            addressEntity.setCityEntity(cityConverter.convertToEntity(dto.getCity()));
        }
        if (dto.getCounty() != null) {
            addressEntity.setCountyEntity(countyConverter.convertToEntity(dto.getCounty()));
        }
        Long userId = dto.getUserId();
        if (dto.getUserId() != null) {
            addressEntity.setUserEntity(userConverter.convertToEntity(userService.getUser(userId)));
        }
        addressEntity.setDistrictId(dto.getDistrictId());
        addressEntity.setZipCode(dto.getZipCode());
        addressEntity.setDetail(dto.getDetail());
        addressEntity.setTitle(dto.getTitle());

        return addressEntity;
    }

    @Override
    public UserAddressDto convertToDto(AddressEntity entity) {
        if (entity == null) {
            return null;
        }
        UserAddressDto addressDto = new UserAddressDto();
        addressDto.setId(entity.getId());
        addressDto.setCreateDate(entity.getCreateDate());
        addressDto.setUpdateDate(entity.getUpdateDate());
        if (entity.getCityEntity() != null) {
            addressDto.setCity(cityConverter.convertToDto(entity.getCityEntity()));
        }
        if(entity.getCountyEntity()!=null){
            addressDto.setCounty(countyConverter.convertToDto(entity.getCountyEntity()));
        }
        if(entity.getUserEntity()!=null){
            Long userId = entity.getUserEntity().getId();
            addressDto.setUserId(userId);
        }
        addressDto.setDistrictId(entity.getDistrictId());
        addressDto.setZipCode(entity.getZipCode());
        addressDto.setDetail(entity.getDetail());
        addressDto.setTitle(entity.getTitle());
        return addressDto;
    }

    public UserAddressDto convert(CreateUserAddressRequest userAddressRequest) {

        if (userAddressRequest == null) {
            return null;
        }
        UserAddressDto userAddressDto = new UserAddressDto();
        userAddressDto.setId(userAddressRequest.getId());
        userAddressDto.setCreateDate(userAddressRequest.getCreateDate());
        userAddressDto.setUpdateDate(userAddressRequest.getUpdateDate());
        if (userAddressRequest.getCityId() != null) {
            Long cityId = userAddressRequest.getCityId();
            userAddressDto.setCity(cityService.getCity(cityId));
        }
        if (userAddressRequest.getCountyId() != null) {
            Long countyId = userAddressRequest.getCountyId();
            userAddressDto.setCounty(countyService.getById(countyId));
        }
        if (userAddressRequest.getUserId() != null) {
            userAddressDto.setUserId(userAddressRequest.getUserId());
        }
        userAddressDto.setDistrictId(userAddressRequest.getDistrictId());
        userAddressDto.setZipCode(userAddressRequest.getZipCode());
        userAddressDto.setDetail(userAddressRequest.getDetail());
        userAddressDto.setTitle(userAddressRequest.getTitle());

        return userAddressDto;
    }


}
