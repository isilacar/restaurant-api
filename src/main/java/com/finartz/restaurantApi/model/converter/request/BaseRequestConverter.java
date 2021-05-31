package com.finartz.restaurantApi.model.converter.request;

import com.finartz.restaurantApi.model.entity.BaseEntity;
import com.finartz.restaurantApi.model.request.BaseRequest;

public interface BaseRequestConverter <T extends BaseEntity,K extends BaseRequest> {

    T convertToEntity(K k);

    K convertToRequest(T t);
}
