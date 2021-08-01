package com.finartz.restaurantApi.dao.impl;

import com.finartz.restaurantApi.dao.CountyRepository;
import com.finartz.restaurantApi.model.entity.CountyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
class CountyRepositoryImpl implements CountyRepository {

    private final EntityManager entityManager;

    @Override
    public List<CountyEntity> findCounties(@Param("cityId") Long cityId) {
        return entityManager.createQuery("select c from CountyEntity c where c.cityEntity.id = :cityId")
                .setParameter("cityId", cityId)
                .getResultList();

    }

    @Override
    public CountyEntity findCounty(Long id) {
        return (CountyEntity) entityManager
                .createQuery("select county from CountyEntity county where county.id=:id")
                .setParameter("id", id)
                .getSingleResult();
    }

}
