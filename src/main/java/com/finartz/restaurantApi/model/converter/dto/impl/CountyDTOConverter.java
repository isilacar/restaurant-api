package com.finartz.restaurantApi.model.converter.dto.impl;

import com.finartz.restaurantApi.model.converter.dto.BaseDTOConverter;
import com.finartz.restaurantApi.model.dto.CountyDto;
import com.finartz.restaurantApi.model.entity.CountyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CountyDTOConverter implements BaseDTOConverter<CountyEntity, CountyDto> {

    private final CityDTOConverter cityConverter;

    @Override
    public CountyEntity convertToEntity(CountyDto dto) {
        CountyEntity countyEntity = new CountyEntity();
        countyEntity.setId(dto.getId());
        countyEntity.setCreateDate(dto.getCreateDate());
        countyEntity.setUpdateDate(dto.getUpdateDate());
        countyEntity.setName(dto.getName());
        countyEntity.setCityEntity(cityConverter.convertToEntity(dto.getCity()));
        return countyEntity;
    }

    @Override
    public CountyDto convertToDto(CountyEntity entity) {
        CountyDto countyDto = new CountyDto();
        countyDto.setId(entity.getId());
        countyDto.setCreateDate(entity.getCreateDate());
        countyDto.setUpdateDate(entity.getUpdateDate());
        countyDto.setName(entity.getName());
        countyDto.setCity(cityConverter.convertToDto(entity.getCityEntity()));
        return countyDto;
    }

    public List<CountyDto> convertToDtoList(List<CountyEntity> countyEntities){
        return countyEntities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<CountyEntity> convertToEntityList(List<CountyDto> countyDtos){
        return countyDtos.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
