package com.finartz.restaurantApi.model.converter.dto.impl;

import com.finartz.restaurantApi.model.converter.dto.BaseDTOConverter;
import com.finartz.restaurantApi.model.dto.AddressDto;
import com.finartz.restaurantApi.model.entity.AddressEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AddressDTOConverter implements BaseDTOConverter<AddressEntity, AddressDto> {

    private final CityDTOConverter cityConverter;
    private final CountyDTOConverter countyConverter;

    @Override
    public AddressEntity convertToEntity(AddressDto dto) {
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
        addressEntity.setDistrictId(dto.getDistrictId());
        addressEntity.setZipCode(dto.getZipCode());
        addressEntity.setDetail(dto.getDetail());
        addressEntity.setTitle(dto.getTitle());

        return addressEntity;
    }

    @Override
    public AddressDto convertToDto(AddressEntity entity) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(entity.getId());
        addressDto.setCreateDate(entity.getCreateDate());
        addressDto.setUpdateDate(entity.getUpdateDate());
        if (entity.getCityEntity() != null) {
            addressDto.setCity(cityConverter.convertToDto(entity.getCityEntity()));
        }
        if (entity.getCountyEntity() != null) {
            addressDto.setCounty(countyConverter.convertToDto(entity.getCountyEntity()));
        }

        addressDto.setDistrictId(entity.getDistrictId());
        addressDto.setZipCode(entity.getZipCode());
        addressDto.setDetail(entity.getDetail());
        addressDto.setTitle(entity.getTitle());

        return addressDto;
    }

    public List<AddressDto> convertToDtoList(List<AddressEntity> addressEntities) {
        return addressEntities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<AddressEntity> convertToEntityList(List<AddressDto> addressDtos) {
        return addressDtos.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
