package com.finartz.restaurantApi.dao.impl;

import com.finartz.restaurantApi.base.repository.BaseRepository;
import com.finartz.restaurantApi.dao.MenuRepository;
import com.finartz.restaurantApi.model.entity.MenuEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class MenuRepositoryImpl extends BaseRepository<MenuEntity> implements MenuRepository {

    private final EntityManager entityManager;

    public MenuRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public MenuEntity saveMenu(MenuEntity menuEntity) {
        save(menuEntity);
        return menuEntity;
    }

    @Override
    public MenuEntity findById(Long menuId) {

        return (MenuEntity) entityManager
                .createQuery("select menu from MenuEntity menu where menu.id =:menuId")
                .setParameter("menuId", menuId)
                .getSingleResult();
    }

    @Override
    public MenuEntity saveMealToMenu(MenuEntity menuEntity) {
        entityManager.merge(menuEntity);
        return menuEntity;
    }

}
