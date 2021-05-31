package com.finartz.restaurantApi.dao.impl;

import com.finartz.restaurantApi.dao.CountyRepository;
import com.finartz.restaurantApi.model.entity.CountyEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CountyRepositoryImpl implements CountyRepository {

    private final EntityManager entityManager;

    public CountyRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<CountyEntity> getAllCountiesByCityId(@Param("cityId") Long cityId) {
        return entityManager.createQuery("select c from CountyEntity c where c.cityEntity.id = :cityId")
                .setParameter("cityId", cityId)
                .getResultList();

    }

    @Override
    public CountyEntity findById(Long id) {
        return (CountyEntity) entityManager
                .createQuery("select county from CountyEntity county where county.id=:id")
                .setParameter("id", id)
                .getSingleResult();
    }

}
