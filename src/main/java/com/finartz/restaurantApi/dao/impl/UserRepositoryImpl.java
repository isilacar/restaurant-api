package com.finartz.restaurantApi.dao.impl;

import com.finartz.restaurantApi.base.repository.BaseRepository;
import com.finartz.restaurantApi.dao.UserRepository;
import com.finartz.restaurantApi.model.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Slf4j
@RequiredArgsConstructor
class UserRepositoryImpl extends BaseRepository<UserEntity> implements UserRepository {

    private final EntityManager entityManager;

    @Transactional
    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        save(userEntity);
        return userEntity;
    }

    @Override
    public Optional<UserEntity> findUser(Long userId) {

        String hql = "select user from UserEntity user left join fetch user.addresses where user.id=:id";
        Query query = (Query) entityManager.createQuery(hql);
        query.setParameter("id", userId);
        UserEntity userEntity = (UserEntity) query.uniqueResult(); //resultList yerine UniqueResult aldım.
        if (userEntity == null) {
            return Optional.empty();
        }
        return Optional.of(userEntity);

    }

    @Override
    public Optional<UserEntity> findByUserName(String userName) {

        String hql = "select user from UserEntity user left join fetch user.addresses where user.userName=:username";
        Query query = (Query) entityManager.createQuery(hql);
        query.setParameter("username", userName);
        UserEntity userEntity = (UserEntity) query.uniqueResult();
        if (userEntity == null) {
            return Optional.empty();
        }
        return Optional.of(userEntity);

    }

}
