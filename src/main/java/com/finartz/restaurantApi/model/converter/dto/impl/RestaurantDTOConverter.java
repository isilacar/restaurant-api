package com.finartz.restaurantApi.model.converter.dto.impl;

import com.finartz.restaurantApi.model.converter.dto.BaseDTOConverter;
import com.finartz.restaurantApi.model.dto.RestaurantDto;
import com.finartz.restaurantApi.model.entity.RestaurantEntity;
import com.finartz.restaurantApi.model.request.CreateRestaurantRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RestaurantDTOConverter implements BaseDTOConverter<RestaurantEntity, RestaurantDto> {

    private final MenuDTOConverter menuConverter;
    private final AddressDTOConverter addressConverter;

    @Override
    public RestaurantEntity convertToEntity(RestaurantDto dto) {
        RestaurantEntity restaurantEntity=new RestaurantEntity();
        restaurantEntity.setId(dto.getId());
        restaurantEntity.setName(dto.getName());
        restaurantEntity.setCreateDate(dto.getCreateDate());
        restaurantEntity.setUpdateDate(dto.getUpdateDate());
        restaurantEntity.setStatus(dto.getStatus());
        restaurantEntity.setLatitude(dto.getLatitude());
        restaurantEntity.setLongitude(dto.getLongitude());
        restaurantEntity.setAddressEntities(addressConverter.convertToEntityList(dto.getAddresses()));
        return restaurantEntity;
    }

    @Override
    public RestaurantDto convertToDto(RestaurantEntity entity) {
        RestaurantDto restaurantDto=new RestaurantDto();
        restaurantDto.setId(entity.getId());
        restaurantDto.setName(entity.getName());
        restaurantDto.setCreateDate(entity.getCreateDate());
        restaurantDto.setUpdateDate(entity.getUpdateDate());
        restaurantDto.setStatus(entity.getStatus());
        if (entity.getMenu() != null) {
            restaurantDto.setMenu(menuConverter.convertToDto(entity.getMenu()));
        }
        restaurantDto.setLatitude(entity.getLatitude());
        restaurantDto.setLongitude(entity.getLongitude());
        if (!entity.getAddressEntities().isEmpty()) {
            restaurantDto.setAddresses(addressConverter.convertToDtoList(entity.getAddressEntities()));
        }
        return restaurantDto;
    }
    public RestaurantDto convertToDto(CreateRestaurantRequest restaurantRequest) {
        RestaurantDto restaurantDto=new RestaurantDto();
        restaurantDto.setId(restaurantRequest.getId());
        restaurantDto.setName(restaurantRequest.getName());
        restaurantDto.setCreateDate(restaurantRequest.getCreateDate());
        restaurantDto.setUpdateDate(restaurantRequest.getUpdateDate());
        restaurantDto.setStatus(restaurantRequest.getStatus());
        restaurantDto.setLatitude(restaurantRequest.getLatitude());
        restaurantDto.setLongitude(restaurantRequest.getLongitude());

        return restaurantDto;
    }

    public List<RestaurantDto> convertToDtoList(List<RestaurantEntity> restaurantEntities){
        return restaurantEntities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<RestaurantEntity> convertToEntityList(List<RestaurantDto> restaurantDtos){
        return restaurantDtos.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

}
