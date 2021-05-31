package com.finartz.restaurantApi.model.converter.request.impl;

import com.finartz.restaurantApi.model.converter.request.BaseRequestConverter;
import com.finartz.restaurantApi.model.entity.MenuEntity;
import com.finartz.restaurantApi.model.request.CreateRestaurantMenuRequest;
import org.springframework.stereotype.Component;

@Component
public class MenuRequestConverter implements BaseRequestConverter<MenuEntity, CreateRestaurantMenuRequest> {

    @Override
    public MenuEntity convertToEntity(CreateRestaurantMenuRequest menuRequest) {
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setId(menuRequest.getId());
        menuEntity.setCreateDate(menuRequest.getCreateDate());
        menuEntity.setUpdateDate(menuRequest.getUpdateDate());

        return menuEntity;
    }

    @Override
    public CreateRestaurantMenuRequest convertToRequest(MenuEntity menuEntity) {
        return null;
    }
}
