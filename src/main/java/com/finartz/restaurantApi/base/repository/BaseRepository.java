package com.finartz.restaurantApi.base.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class BaseRepository <T>{

    @Autowired
    private EntityManager entityManager;

    public void save(T t){
        entityManager.persist(t);
    }

    public T saveOrUpdate(T t){
        entityManager.merge(t);
        return t;
    }



}
