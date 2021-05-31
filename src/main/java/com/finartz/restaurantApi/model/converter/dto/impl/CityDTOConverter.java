package com.finartz.restaurantApi.model.converter.dto.impl;

import com.finartz.restaurantApi.model.converter.dto.BaseDTOConverter;
import com.finartz.restaurantApi.model.dto.CityDto;
import com.finartz.restaurantApi.model.entity.CityEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityDTOConverter implements BaseDTOConverter<CityEntity, CityDto> {

    @Override
    public CityEntity convertToEntity(CityDto dto) {
        CityEntity cityEntity=new CityEntity();
        cityEntity.setId(dto.getId());
        cityEntity.setCreateDate(dto.getCreateDate());
        cityEntity.setUpdateDate(dto.getUpdateDate());
        cityEntity.setName(dto.getName());
        cityEntity.setLicensePlate(dto.getLicensePlate());
        return cityEntity;
    }

    @Override
    public CityDto convertToDto(CityEntity entity) {
       CityDto cityDto=new CityDto();
        cityDto.setId(entity.getId());
        cityDto.setCreateDate(entity.getCreateDate());
       cityDto.setUpdateDate(entity.getUpdateDate());
        cityDto.setName(entity.getName());
        cityDto.setLicensePlate(entity.getLicensePlate());

        return cityDto;
    }

    public List<CityDto> convertToDtoList(List<CityEntity> cityEntities){
        return cityEntities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<CityEntity> convertToEntityList(List<CityDto> cityDtos){
        return cityDtos.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
