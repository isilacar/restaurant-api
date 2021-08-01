package com.finartz.restaurantApi.service.foundation.impl;

import com.finartz.restaurantApi.dao.RestaurantRepository;
import com.finartz.restaurantApi.error.RestaurantError;
import com.finartz.restaurantApi.exception.ResourceNotFoundException;
import com.finartz.restaurantApi.model.converter.dto.impl.RestaurantDTOConverter;
import com.finartz.restaurantApi.model.converter.dto.impl.UserDTOConverter;
import com.finartz.restaurantApi.model.dto.RestaurantDto;
import com.finartz.restaurantApi.model.dto.UserDto;
import com.finartz.restaurantApi.model.entity.RestaurantEntity;
import com.finartz.restaurantApi.model.entity.UserEntity;
import com.finartz.restaurantApi.model.enumeration.RestaurantStatus;
import com.finartz.restaurantApi.security.TokenManager;
import com.finartz.restaurantApi.service.foundation.RestaurantFoundationService;
import com.finartz.restaurantApi.service.foundation.UserFoundationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantFoundationServiceImpl implements RestaurantFoundationService {

    private final RestaurantRepository restaurantRepository;
    private final TokenManager tokenManager;
    private final HttpServletRequest httpServletRequest;
    private final UserFoundationService userFoundationService;
    private final UserDTOConverter userDTOConverter;
    private final RestaurantDTOConverter restaurantConverter;

    @Transactional
    @Override
    public RestaurantDto saveRestaurant(RestaurantDto restaurant) {
        RestaurantEntity restaurantEntity = restaurantConverter.convertToEntity(restaurant);
        restaurantRepository.saveRestaurant(restaurantEntity);
        return restaurantConverter.convertToDto(restaurantEntity) ;
    }

    @Override
    public RestaurantDto getRestaurant(Long id) {
        RestaurantEntity restaurantEntity = restaurantRepository.findRestaurant(id).orElseThrow(() -> new ResourceNotFoundException(RestaurantError.RESTAURANT_NOT_FOUND));
        return restaurantConverter.convertToDto(restaurantEntity);

    }

    @Override
    public List<RestaurantDto> getAllNearestRestaurantByApproved() {
        final String BEARER = "Bearer ";
        final String AUTHORIZATION = "Authorization";
        String userName = null;
        String token;
        String tokenHeader = httpServletRequest.getHeader(AUTHORIZATION);
        if (tokenHeader != null && tokenHeader.startsWith(BEARER)) {
            token = tokenHeader.substring(BEARER.length());
                userName = tokenManager.getUsernameFromToken(token);
        }
        UserDto byUsername = userFoundationService.getByUsername(userName);
        UserEntity currentUser = userDTOConverter.convertToEntity(byUsername);
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
        return restaurantConverter.convertToDtoList(orderedRestaurants);
    }

    @Transactional
    @Override
    public void updateRestaurant(Long id, RestaurantDto restaurantDto) {  //updatesRestaurantRequest oluştur,SON KARAR BÖYLE KALSIN
       Optional<RestaurantEntity> optionalRestaurantEntity = restaurantRepository.findRestaurant(id);
        RestaurantEntity restaurantEntity = optionalRestaurantEntity.get();
        if (restaurantDto.getName() != null) {
            restaurantEntity.setName(restaurantDto.getName());
        }
        if (restaurantDto.getStatus() != null) {
            restaurantEntity.setStatus(restaurantDto.getStatus());
        }
        if (restaurantDto.getLatitude() != null) {
            restaurantEntity.setLatitude(restaurantDto.getLatitude());
        }
        if (restaurantDto.getLongitude() != null) {
            restaurantEntity.setLongitude(restaurantDto.getLongitude());
        }
        restaurantRepository.updateRestaurant(id, restaurantEntity);
    }

    @Override
    public List<RestaurantDto> getAllRestaurantByStatus(RestaurantStatus statusEnum) {
        List<RestaurantEntity> restaurants = restaurantRepository.findRestaurantByStatus(statusEnum);
        return restaurantConverter.convertToDtoList(restaurants);
    }

    @Override
    public List<RestaurantDto> getAllRestaurants() {
        List<RestaurantEntity> allRestaurants=restaurantRepository.getAll();
        return restaurantConverter.convertToDtoList(allRestaurants);
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
}
