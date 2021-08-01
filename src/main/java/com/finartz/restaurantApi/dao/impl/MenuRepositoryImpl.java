package com.finartz.restaurantApi.dao.impl;

import com.finartz.restaurantApi.base.repository.BaseRepository;
import com.finartz.restaurantApi.dao.MenuRepository;
import com.finartz.restaurantApi.model.entity.MenuEntity;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
class MenuRepositoryImpl extends BaseRepository<MenuEntity> implements MenuRepository {

    private final EntityManager entityManager;

    @Transactional
    @Override
    public MenuEntity saveMenu(MenuEntity menuEntity) {
        save(menuEntity);
        return menuEntity;
    }

    @Override
    public Optional<MenuEntity> findMenu(Long menuId) {

            String hql="select menu from MenuEntity menu where menu.id =:menuId";
            Query query = (Query) entityManager.createQuery(hql);
            query.setParameter("menuId",menuId);
           MenuEntity menuEntity =(MenuEntity)query.uniqueResult();
        if(menuEntity==null){
            return Optional.empty();
        }

        return Optional.of(menuEntity);
    }

    @Override
    public MenuEntity saveMealToMenu(MenuEntity menuEntity) {
        entityManager.merge(menuEntity);
        return menuEntity;
    }

}
