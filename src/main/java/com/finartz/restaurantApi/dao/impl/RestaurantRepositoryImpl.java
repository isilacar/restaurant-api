package com.finartz.restaurantApi.dao.impl;

import com.finartz.restaurantApi.base.repository.BaseRepository;
import com.finartz.restaurantApi.dao.RestaurantRepository;
import com.finartz.restaurantApi.model.entity.RestaurantEntity;
import com.finartz.restaurantApi.model.enumeration.RestaurantStatus;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
class RestaurantRepositoryImpl extends BaseRepository<RestaurantEntity> implements RestaurantRepository {

    private final EntityManager entityManager;

    @Transactional
    @Override
    public RestaurantEntity saveRestaurant(RestaurantEntity restaurantEntity) {
        save(restaurantEntity);
        return restaurantEntity;
    }

    @Override
    public Optional<RestaurantEntity> findRestaurant(Long restaurantId) {

            String hql = "select restaurant from RestaurantEntity restaurant" +
                    " left join fetch restaurant.addressEntities WHERE restaurant.id= :restId";
            Query query = (Query) entityManager.createQuery(hql);
            query.setParameter("restId", restaurantId);
            RestaurantEntity restaurantEntity=(RestaurantEntity) query.uniqueResult();
            if(restaurantEntity==null){
                return Optional.empty();
            }

        return Optional.of(restaurantEntity);
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

    @Transactional
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
    public List<RestaurantEntity> findRestaurantByStatus(RestaurantStatus statusEnum) {
        String hql = "select restaurant from RestaurantEntity restaurant left join fetch restaurant.addressEntities " +
                "WHERE restaurant.status=:statusEnum";
        Query query = (Query) entityManager.createQuery(hql);
        query.setParameter("statusEnum",statusEnum);
        List resultList = query.getResultList();

        return resultList;

    }

    @Override
    public List<RestaurantEntity> getAll() {
        String hql="select restaurant from RestaurantEntity restaurant left join fetch restaurant.addressEntities";
        Query query=(Query) entityManager.createQuery(hql);
        List resultList= query.getResultList();
        return resultList;
    }

}

