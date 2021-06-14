package com.finartz.restaurantApi.dao.impl;

import com.finartz.restaurantApi.base.repository.BaseRepository;
import com.finartz.restaurantApi.dao.RestaurantRepository;
import com.finartz.restaurantApi.error.RestaurantError;
import com.finartz.restaurantApi.exception.ResourceNotFoundException;
import com.finartz.restaurantApi.model.entity.RestaurantEntity;
import com.finartz.restaurantApi.model.enumeration.StatusEnum;
import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public class RestaurantRepositoryImpl extends BaseRepository<RestaurantEntity> implements RestaurantRepository {

    public RestaurantRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private final EntityManager entityManager;

    @Override
    public RestaurantEntity saveRestaurant(RestaurantEntity restaurantEntity) {
        save(restaurantEntity);
        return restaurantEntity;
    }

    @Override
    public RestaurantEntity findById(Long restaurantId) {

        RestaurantEntity restaurantEntity=null;
        try{
            String hql = "select restaurant from RestaurantEntity restaurant" +
                    " left join fetch restaurant.addressEntities WHERE restaurant.id= :restId";
            Query query = (Query) entityManager.createQuery(hql);
            query.setParameter("restId", restaurantId);
            restaurantEntity=(RestaurantEntity) query.getSingleResult();
        }catch (NoResultException exception){
            throw new ResourceNotFoundException(RestaurantError.RESTAURANT_NOT_FOUND);
        }

        return restaurantEntity;
    }

    @Override
    public List<RestaurantEntity> findNearestRestaurantByStatusApproved(Long userId) {
        final double distance = 0.0500;
        String hql = "select restaurant from RestaurantEntity restaurant,UserEntity user left join fetch restaurant.addressEntities "
                + "WHERE restaurant.status='APPROVED' and user.id=:id " +
                "and (restaurant.longitude <= user.longitude + :distance and restaurant.longitude >= user.longitude - :distance) " +
                " and (restaurant.latitude<=user.latitude + :distance and restaurant.latitude>=user.latitude-:distance)";
        Query query = (Query) entityManager.createQuery(hql);
        query.setParameter("id", userId);
        query.setParameter("distance", distance);
        return (List<RestaurantEntity>) query.getResultList();
    }

    @Modifying
    @Override
    public void updateRestaurant(Long id, RestaurantEntity restaurantEntity) {
        String hql = "update RestaurantEntity restaurant set restaurant.status=:status,restaurant.name=:name," +
                "restaurant.latitude=:latitude,restaurant.longitude=:longitude where restaurant.id=:id";
        Query query = (Query) entityManager.createQuery(hql);
        query.setParameter("id", id);
        query.setParameter("status", restaurantEntity.getStatus());
        query.setParameter("name", restaurantEntity.getName());
        query.setParameter("latitude", restaurantEntity.getLatitude());
        query.setParameter("longitude", restaurantEntity.getLongitude());
        query.executeUpdate();
    }

    @Override
    public List<RestaurantEntity> findRestaurantByStatus(StatusEnum statusEnum) {
        String hql = "select restaurant from RestaurantEntity restaurant left join fetch restaurant.addressEntities " +
                "WHERE restaurant.status=:statusEnum";
        Query query = (Query) entityManager.createQuery(hql);
        query.setParameter("statusEnum",statusEnum);
        List resultList = query.getResultList();

        return resultList;

    }
/*
    @Modifying
    @Override
    public void updateStatus(Long restaurantId, StatusEnum statusEnum) {
        String hql = "update RestaurantEntity restaurant set restaurant.status=:statusEnum" +
                " where restaurant.id=:restaurantId";

        Query query = (Query) entityManager.createQuery(hql);
        query.setParameter("statusEnum", statusEnum);
        query.setParameter("restaurantId", restaurantId);
        query.executeUpdate();

    }



    //findrestaurantbystatus diyip hangi statüsü istiyosak onu parametre olarak iste


    @Override
    public List<RestaurantEntity> findRestaurantByStatusAwaiting() {

        String hql = "select restaurant from RestaurantEntity restaurant left join fetch restaurant.addressEntities " +
                "WHERE restaurant.status= 'AWAITING'";
        Query query = (Query) entityManager.createQuery(hql);
        List resultList = query.getResultList();

        return resultList;

    }

    @Override
    public List<RestaurantEntity> findRestaurantByStatusApproved() {
        String hql = "select restaurant from RestaurantEntity restaurant left join fetch restaurant.addressEntities " +
                "WHERE restaurant.status= 'APPROVED'";
        Query query = (Query) entityManager.createQuery(hql);
        List resultList = query.getResultList();
        return resultList;
    }

 */
}

