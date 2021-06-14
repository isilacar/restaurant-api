package com.finartz.restaurantApi.dao.impl;

import com.finartz.restaurantApi.base.repository.BaseRepository;
import com.finartz.restaurantApi.dao.UserRepository;
import com.finartz.restaurantApi.error.UserError;
import com.finartz.restaurantApi.exception.ResourceNotFoundException;
import com.finartz.restaurantApi.model.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

@Repository
@Transactional
@Slf4j
public class UserRepositoryImpl extends BaseRepository<UserEntity> implements UserRepository {

    private final EntityManager entityManager;

    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        save(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity findById(Long userId) {
     /*   String hql = "select user from UserEntity user left join fetch user.addresses where user.id=:id";
        UserEntity userEntity = null;
        try {
            Query query = (Query) entityManager.createQuery(hql);
            query.setParameter("id", userId);
            userEntity = (UserEntity) query.getSingleResult();
        } catch (UserNotFoundException exception) {
            log.info(exception.getMessage());
        }
        return userEntity;

        return (UserEntity) entityManager
                .createQuery("select user from UserEntity user left join fetch user.addresses where user.id=:id")
                .setParameter("id", userId)
                .getSingleResult();
      */
        UserEntity userEntity = null;
        try {
            String hql = "select user from UserEntity user left join fetch user.addresses where user.id=:id";
            Query query = (Query) entityManager.createQuery(hql);
            query.setParameter("id", userId);
            userEntity = (UserEntity) query.getSingleResult();
        } catch (NoResultException exception) {
           throw new ResourceNotFoundException(UserError.USER_NOT_FOUND);
        }

        return userEntity;

    }

    @Override
    public UserEntity findByUserName(String userName) {
        UserEntity userEntity = null;
        try {
            String hql = "select user from UserEntity user left join fetch user.addresses where user.userName=:username";
            Query query = (Query) entityManager.createQuery(hql);
            query.setParameter("username", userName);
            userEntity = (UserEntity) query.getSingleResult();
        } catch (NoResultException exception) {
            throw new ResourceNotFoundException(UserError.USER_NOT_FOUND);
        }

        return userEntity;

    }

}
