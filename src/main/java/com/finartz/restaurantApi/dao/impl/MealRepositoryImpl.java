package com.finartz.restaurantApi.dao.impl;

import com.finartz.restaurantApi.base.repository.BaseRepository;
import com.finartz.restaurantApi.dao.MealRepository;
import com.finartz.restaurantApi.error.MealError;
import com.finartz.restaurantApi.exception.ResourceNotFoundException;
import com.finartz.restaurantApi.model.entity.MealEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;

@Repository
@Transactional
public class MealRepositoryImpl extends BaseRepository<MealEntity> implements MealRepository {

    private final EntityManager entityManager;

    public MealRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public MealEntity saveMeal(MealEntity mealEntity) {
        save(mealEntity);
        return mealEntity;
    }

    @Override
    public MealEntity findById(Long mealId) {
        MealEntity mealEntity=null;
        try{
            String hql="select m from MealEntity m where m.id=:id";
            Query query = entityManager.createQuery(hql);
            query.setParameter("id",mealId);
            mealEntity=(MealEntity) query.getSingleResult();
        }
        catch (NoResultException exception){
            throw new ResourceNotFoundException(MealError.MEAL_NOT_FOUND);
        }

        return mealEntity;
        /*
        return (MealEntity) entityManager
                .createQuery("select m from MealEntity m where m.id=:id")
                .setParameter("id", mealId)
                .getSingleResult();

         */
    }

    @Override
    public List<MealEntity> findByName(String mealName) {
        return entityManager.createQuery("select m from MealEntity m where m.name like concat('%',:name,'%')")
                .setParameter("name", mealName.toLowerCase(new Locale("tr", "TR")))
                .getResultList();

    }

}
