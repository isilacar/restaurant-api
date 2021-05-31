package com.finartz.restaurantApi.dao.impl;

import com.finartz.restaurantApi.dao.CityRepository;
import com.finartz.restaurantApi.model.entity.CityEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CityRepositoryImpl implements CityRepository {

    private final EntityManager entityManager;

    public CityRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<CityEntity> getAll() {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<CityEntity> cityQuery = criteriaBuilder.createQuery(CityEntity.class);
        Root<CityEntity> cityEntityRoot = cityQuery.from(CityEntity.class);
        cityQuery.select(cityEntityRoot);

        List<CityEntity> resultList = session.createQuery(cityQuery).getResultList();

        return resultList;
    }

    @Override
    public CityEntity findById(Long id) {
        return (CityEntity) entityManager
                .createQuery("select city from CityEntity city where city.id=:id")
                .setParameter("id", id)
                .getSingleResult();

    }
}
