package com.finartz.restaurantApi.dao;

import com.finartz.restaurantApi.model.entity.MenuEntity;

import java.util.Optional;

public interface MenuRepository {

    MenuEntity saveMenu(MenuEntity menuEntity);

    Optional<MenuEntity> findMenu(Long menuId);

    MenuEntity saveMealToMenu(MenuEntity menuEntity);

}
