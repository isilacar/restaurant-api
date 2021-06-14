package com.finartz.restaurantApi.dao.impl;

import com.finartz.restaurantApi.base.repository.BaseRepository;
import com.finartz.restaurantApi.dao.MenuRepository;
import com.finartz.restaurantApi.error.MenuError;
import com.finartz.restaurantApi.exception.ResourceNotFoundException;
import com.finartz.restaurantApi.model.entity.MenuEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
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
        MenuEntity menuEntity=null;
        try{
            String hql="select menu from MenuEntity menu where menu.id =:menuId";
            Query query = entityManager.createQuery(hql);
            query.setParameter("menuId",menuId);
           menuEntity =(MenuEntity) query.getSingleResult();
        }catch (NoResultException exception){
            throw new ResourceNotFoundException(MenuError.MENU_NOT_FOUND);
        }

        return menuEntity;
        /*
        return (MenuEntity) entityManager
                .createQuery("select menu from MenuEntity menu where menu.id =:menuId")
                .setParameter("menuId", menuId)
                .getSingleResult();

         */
    }

    @Override
    public MenuEntity saveMealToMenu(MenuEntity menuEntity) {
        entityManager.merge(menuEntity);
        return menuEntity;
    }

}
