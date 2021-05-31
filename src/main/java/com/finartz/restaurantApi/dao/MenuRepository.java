package com.finartz.restaurantApi.dao;

import com.finartz.restaurantApi.model.entity.MenuEntity;

public interface MenuRepository {

    MenuEntity saveMenu(MenuEntity menuEntity);

    MenuEntity findById(Long menuId);

    MenuEntity saveMealToMenu(MenuEntity menuEntity);

}
