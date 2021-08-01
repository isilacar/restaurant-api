package com.finartz.restaurantApi.model.converter.dto.impl;

import com.finartz.restaurantApi.model.converter.dto.BaseDTOConverter;
import com.finartz.restaurantApi.model.dto.RestaurantAddressDto;
import com.finartz.restaurantApi.model.dto.RestaurantDto;
import com.finartz.restaurantApi.model.entity.AddressEntity;
import com.finartz.restaurantApi.model.request.CreateRestaurantAddressRequest;
import com.finartz.restaurantApi.service.aggregation.CityService;
import com.finartz.restaurantApi.service.aggregation.CountyService;
import com.finartz.restaurantApi.service.aggregation.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantAddressDtoConverter implements BaseDTOConverter<AddressEntity, RestaurantAddressDto> {

    private final CityDTOConverter cityConverter;
    private final CountyDTOConverter countyConvert;
    private final RestaurantService restaurantService;
    private final RestaurantDTOConverter restaurantDTOConverter;
    private final CityService cityService;
    private final CountyService countyService;


    @Override
    public AddressEntity convertToEntity(RestaurantAddressDto dto) {
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
            addressEntity.setCountyEntity(countyConvert.convertToEntity(dto.getCounty()));
        }
        if (dto.getRestaurantId() != null) {
            RestaurantDto restaurantDto = restaurantService.getById(dto.getRestaurantId());
            addressEntity.setRestaurantEntity(restaurantDTOConverter.convertToEntity(restaurantDto));
        }
        addressEntity.setDistrictId(dto.getDistrictId());
        addressEntity.setZipCode(dto.getZipCode());
        addressEntity.setDetail(dto.getDetail());
        addressEntity.setTitle(dto.getTitle());

        return addressEntity;
    }

    @Override
    public RestaurantAddressDto convertToDto(AddressEntity entity) {
        if (entity == null) {
            return null;
        }
        RestaurantAddressDto addressDto = new RestaurantAddressDto();
        addressDto.setId(entity.getId());
        addressDto.setCreateDate(entity.getCreateDate());
        addressDto.setUpdateDate(entity.getUpdateDate());
        if (entity.getCountyEntity() != null) {
            addressDto.setCounty(countyConvert.convertToDto(entity.getCountyEntity()));
        }
        if (entity.getCityEntity() != null) {
            addressDto.setCity(cityConverter.convertToDto(entity.getCityEntity()));
        }
        if (entity.getRestaurantEntity() != null) {
            Long restaurantId = entity.getRestaurantEntity().getId();
            addressDto.setRestaurantId(restaurantId);
        }
        addressDto.setDistrictId(entity.getDistrictId());
        addressDto.setZipCode(entity.getZipCode());
        addressDto.setDetail(entity.getDetail());
        addressDto.setTitle(entity.getTitle());

        return addressDto;
    }

    public RestaurantAddressDto convert(CreateRestaurantAddressRequest restaurantAddressRequest) {
        if (restaurantAddressRequest == null) {
            return null;
        }
        RestaurantAddressDto addressDto = new RestaurantAddressDto();
        addressDto.setId(restaurantAddressRequest.getId());
        addressDto.setCreateDate(restaurantAddressRequest.getCreateDate());
        addressDto.setUpdateDate(restaurantAddressRequest.getUpdateDate());
        if (restaurantAddressRequest.getCityId() != null) {
            Long cityId = restaurantAddressRequest.getCityId();
            addressDto.setCity(cityService.getCity(cityId));
        }
        if (restaurantAddressRequest.getCountyId() != null) {
            Long countyId = restaurantAddressRequest.getCountyId();
            addressDto.setCounty(countyService.getById(countyId));
        }
        if (restaurantAddressRequest.getRestaurantId() != null) {
            Long restaurantId = restaurantAddressRequest.getRestaurantId();
            addressDto.setRestaurantId(restaurantId);
        }
        addressDto.setDistrictId(restaurantAddressRequest.getDistrictId());
        addressDto.setZipCode(restaurantAddressRequest.getZipCode());
        addressDto.setDetail(restaurantAddressRequest.getDetail());
        addressDto.setTitle(restaurantAddressRequest.getTitle());
        return addressDto;
    }
}
