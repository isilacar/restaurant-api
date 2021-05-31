package com.finartz.restaurantApi.service.foundation.impl;

import com.finartz.restaurantApi.dao.RestaurantRepository;
import com.finartz.restaurantApi.model.converter.request.impl.RestaurantRequestConverter;
import com.finartz.restaurantApi.model.entity.RestaurantEntity;
import com.finartz.restaurantApi.model.entity.UserEntity;
import com.finartz.restaurantApi.model.enumeration.StatusEnum;
import com.finartz.restaurantApi.model.request.CreateRestaurantRequest;
import com.finartz.restaurantApi.security.TokenManager;
import com.finartz.restaurantApi.service.foundation.RestaurantFoundationService;
import com.finartz.restaurantApi.service.foundation.UserFoundationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
@Slf4j
public class RestaurantFoundationServiceImpl implements RestaurantFoundationService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantRequestConverter restaurantRequestConverter;
    private final TokenManager tokenManager;
    private final HttpServletRequest httpServletRequest;
    private final UserFoundationService userFoundationService;

    public RestaurantFoundationServiceImpl(RestaurantRepository restaurantRepository,
                                           RestaurantRequestConverter restaurantRequestConverter,
                                           TokenManager tokenManager,
                                           HttpServletRequest httpServletRequest,
                                           UserFoundationService userFoundationService) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantRequestConverter = restaurantRequestConverter;
        this.tokenManager = tokenManager;
        this.httpServletRequest = httpServletRequest;
        this.userFoundationService = userFoundationService;
    }

    @Override
    public RestaurantEntity saveRestaurant(CreateRestaurantRequest createRestaurantRequest) {
        return restaurantRepository.saveRestaurant(restaurantRequestConverter.convertToEntity(createRestaurantRequest));
    }

    @Override
    public RestaurantEntity getById(Long id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public List<RestaurantEntity> getAllNearestRestaurantByApproved() {
        final String BEARER = "Bearer ";
        final String AUTHORIZATION = "Authorization";
        String userName = null;
        String token;
        String tokenHeader = httpServletRequest.getHeader(AUTHORIZATION);
        if (tokenHeader != null && tokenHeader.startsWith(BEARER)) {
            token = tokenHeader.substring(BEARER.length());
            userName = tokenManager.getUsernameFromToken(token);
        }
        UserEntity currentUser = userFoundationService.getByUsername(userName);
        if (currentUser == null) {
            log.info("User not found..");
            throw new RuntimeException("User not found..");
        }
        List<RestaurantEntity> nearestRestaurantEntities = restaurantRepository.findNearestRestaurantByStatusApproved(currentUser.getId());
        Map<Double, RestaurantEntity> entityMap = new HashMap<>();
        for (RestaurantEntity restaurantEntity : nearestRestaurantEntities) {
            double distance = distance(currentUser, restaurantEntity);
            entityMap.put(distance, restaurantEntity);
            log.info("distance between " + currentUser.getUserName() + " and " + restaurantEntity.getName() + " is {}KM", distance);
        }
        List<RestaurantEntity> orderedRestaurants = new ArrayList<>();
        TreeSet<Double> keys = new TreeSet<>(entityMap.keySet());
        for (Double key : keys) {
            RestaurantEntity value = entityMap.get(key);
            orderedRestaurants.add(value);
            log.info("ordered restaurant " + entityMap.get(key).getName());
        }
        return orderedRestaurants;
    }

    @Override
    public void updateRestaurant(Long id, CreateRestaurantRequest createRestaurantRequest) {
        RestaurantEntity restaurantEntity = restaurantRepository.findById(id);
        if (createRestaurantRequest.getName() != null) {
            restaurantEntity.setName(createRestaurantRequest.getName());
        }
        if (createRestaurantRequest.getStatus() != null) {
            restaurantEntity.setStatus(createRestaurantRequest.getStatus());
        }
        if (createRestaurantRequest.getLatitude() != null) {
            restaurantEntity.setLatitude(createRestaurantRequest.getLatitude());
        }
        if (createRestaurantRequest.getLongitude() != null) {
            restaurantEntity.setLongitude(createRestaurantRequest.getLongitude());
        }
       // restaurantEntity.setUpdateDate(LocalDate.now());
        restaurantRepository.updateRestaurant(id, restaurantEntity);
    }

    @Override
    public List<RestaurantEntity> getAllRestaurantByStatus(StatusEnum statusEnum) {
        return restaurantRepository.findRestaurantByStatus(statusEnum);
    }

    public static double distance(UserEntity userEntity, RestaurantEntity restaurantEntity) {
        Double lat1 = userEntity.getLatitude();
        Double lon1 = userEntity.getLongitude();

        Double lat2 = restaurantEntity.getLatitude();
        Double lon2 = restaurantEntity.getLongitude();

        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        } else {
            double diff = lon1 - lon2; //boylam farkını bul
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
                    + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(diff));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            dist *= 1.609344; //KM cinsinden uzaklığı
            return (dist);
        }
    }



     /*
       @Override
       public List<RestaurantEntity> getAllRestaurantByApproved() {
           return restaurantRepository.findRestaurantByStatusApproved();
    }


           @Override
           public void updateStatus(Long id, StatusEnum status) {
               restaurantRepository.updateStatus(id,status);
           }



       @Override
       public List<RestaurantEntity> getAllRestaurantByAwaiting() {
           return restaurantRepository.findRestaurantByStatusAwaiting();
       }
   */
}
