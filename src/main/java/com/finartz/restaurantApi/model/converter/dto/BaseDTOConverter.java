package com.finartz.restaurantApi.model.converter.dto;

import com.finartz.restaurantApi.model.dto.BaseDto;
import com.finartz.restaurantApi.model.entity.BaseEntity;

public interface BaseDTOConverter<T extends BaseEntity,K extends BaseDto>{

    T convertToEntity(K dto);
    K convertToDto(T entity);
}
