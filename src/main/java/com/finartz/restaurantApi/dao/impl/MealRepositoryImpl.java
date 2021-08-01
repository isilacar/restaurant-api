package com.finartz.restaurantApi.dao.impl;

import com.finartz.restaurantApi.base.repository.BaseRepository;
import com.finartz.restaurantApi.dao.MealRepository;
import com.finartz.restaurantApi.model.entity.MealEntity;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
class MealRepositoryImpl extends BaseRepository<MealEntity> implements MealRepository {

    private final EntityManager entityManager;

    @Transactional
    @Override
    public MealEntity saveMeal(MealEntity mealEntity) {
        save(mealEntity);
        return mealEntity;
    }

    @Override
    public Optional<MealEntity> findMeal(Long mealId) {

            String hql="select m from MealEntity m where m.id=:id";
            Query query = (Query) entityManager.createQuery(hql);
            query.setParameter("id",mealId);
            MealEntity mealEntity=(MealEntity) query.uniqueResult();
            if (mealEntity==null){
                return Optional.empty();
            }

        return Optional.of(mealEntity);
    }

    @Override
    public List<MealEntity> findByName(String mealName) {


        return entityManager.createQuery("select m from MealEntity m where m.name like concat('%',:name,'%')")
                .setParameter("name", mealName.toLowerCase(new Locale("tr", "TR")))
                .getResultList();

    }

}
