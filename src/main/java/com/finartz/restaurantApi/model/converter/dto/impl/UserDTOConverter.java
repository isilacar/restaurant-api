package com.finartz.restaurantApi.model.converter.dto.impl;

import com.finartz.restaurantApi.model.converter.dto.BaseDTOConverter;
import com.finartz.restaurantApi.model.dto.UserDto;
import com.finartz.restaurantApi.model.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserDTOConverter implements BaseDTOConverter<UserEntity, UserDto> {

    private final AddressDTOConverter addressConverter;

    public UserDTOConverter(AddressDTOConverter addressConverter) {
        this.addressConverter = addressConverter;
    }

    @Override
    public UserEntity convertToEntity(UserDto dto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(dto.getId());
        userEntity.setCreateDate(dto.getCreateDate());
        userEntity.setUpdateDate(dto.getUpdateDate());
        userEntity.setRole(dto.getRole());
        userEntity.setUserName(dto.getUserName());
        userEntity.setPassword(dto.getPassword());
        userEntity.setLatitude(dto.getLatitude());
        userEntity.setLongitude(dto.getLongitude());
        userEntity.setEMail(dto.getEMail());
        userEntity.setAddresses(addressConverter.convertToEntityList(dto.getAddresses()));
        return userEntity;
    }

    @Override
    public UserDto convertToDto(UserEntity entity) {
        UserDto userDto = new UserDto();
        userDto.setId(entity.getId());
        userDto.setCreateDate(entity.getCreateDate());
        userDto.setUpdateDate(entity.getUpdateDate());
        userDto.setRole(entity.getRole());
        userDto.setUserName(entity.getUserName());
        userDto.setPassword(entity.getPassword());
        userDto.setLatitude(entity.getLatitude());
        userDto.setLongitude(entity.getLongitude());
        userDto.setEMail(entity.getEMail());
        userDto.setAddresses(addressConverter.convertToDtoList(entity.getAddresses()));
        return userDto;
    }
}
