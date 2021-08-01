package com.finartz.restaurantApi.service.aggregation.impl;

import com.finartz.restaurantApi.error.NotAllowedError;
import com.finartz.restaurantApi.exception.ForbiddenException;
import com.finartz.restaurantApi.model.converter.dto.impl.RestaurantDTOConverter;
import com.finartz.restaurantApi.model.converter.dto.impl.UserDTOConverter;
import com.finartz.restaurantApi.model.dto.RestaurantDto;
import com.finartz.restaurantApi.model.entity.UserEntity;
import com.finartz.restaurantApi.model.enumeration.RestaurantStatus;
import com.finartz.restaurantApi.model.enumeration.UserRoleStatus;
import com.finartz.restaurantApi.model.request.CreateRestaurantRequest;
import com.finartz.restaurantApi.security.TokenManager;
import com.finartz.restaurantApi.service.aggregation.RestaurantService;
import com.finartz.restaurantApi.service.aggregation.UserService;
import com.finartz.restaurantApi.service.foundation.RestaurantFoundationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantDTOConverter restaurantConverter;
    private final RestaurantFoundationService restaurantFoundationService;
    private final TokenManager tokenManager;
    private final HttpServletRequest httpServletRequest;
    private final UserDTOConverter userDTOConverter;
    private final UserService userService;

    @Override
    public RestaurantDto saveRestaurant(CreateRestaurantRequest createRestaurantRequest) {
        RestaurantDto restaurantDto = restaurantConverter.convertToDto(createRestaurantRequest);
        return restaurantFoundationService.saveRestaurant(restaurantDto);
    }

    @Override
    public RestaurantDto getById(Long id) {
        return restaurantFoundationService.getRestaurant(id);
    }

    @Override
    public void updateRestaurant(Long id, CreateRestaurantRequest createRestaurantRequest) {
        RestaurantDto restaurantDto = restaurantConverter.convertToDto(createRestaurantRequest);
        restaurantFoundationService.updateRestaurant(id, restaurantDto);
    }

    @Override
    public List<RestaurantDto> getRestaurants(RestaurantStatus restaurantStatus) {
        final String BEARER = "Bearer ";
        final String AUTHORIZATION = "Authorization";
        String userName = null;
        String token;
        String tokenHeader = httpServletRequest.getHeader(AUTHORIZATION);
        if (tokenHeader != null && tokenHeader.startsWith(BEARER)) {
            token = tokenHeader.substring(BEARER.length());
            userName = tokenManager.getUsernameFromToken(token);
        }
        UserEntity currentUser = userDTOConverter.convertToEntity(userService.getByUsername(userName));
        UserRoleStatus role = currentUser.getRole();
        List<RestaurantDto> allRestaurants = null;
        if (role == UserRoleStatus.ADMIN) {
            if(restaurantStatus==null){
            allRestaurants=restaurantFoundationService.getAllRestaurants();
            }else{
            allRestaurants = restaurantFoundationService.getAllRestaurantByStatus(restaurantStatus);
            }
        }
        if (role == UserRoleStatus.USER) {

            if (restaurantStatus != RestaurantStatus.APPROVED) {
                throw new ForbiddenException(NotAllowedError.DO_NOT_HAVE_PERMISSION);
            }

            allRestaurants = restaurantFoundationService.getAllNearestRestaurantByApproved();
        }
        if (role == UserRoleStatus.SELLER) {
            if (restaurantStatus != RestaurantStatus.APPROVED) {
                throw new ForbiddenException(NotAllowedError.DO_NOT_HAVE_PERMISSION);
            }
            allRestaurants = restaurantFoundationService.getAllRestaurantByStatus(RestaurantStatus.APPROVED);
        }
        return allRestaurants;
    }











/*
    @Override
    public List<RestaurantDto> getAllNearestRestaurantByApproved() {
        return restaurantFoundationService.getAllNearestRestaurantByApproved();
    }


    @Override
    public List<RestaurantDto> getAllRestaurantByApproved() {
        return restaurantFoundationService.getAllRestaurantByStatus(RestaurantStatus.APPROVED);
    }

    @Override
    public List<RestaurantDto> getAllRestaurantByAwaiting() {
        return restaurantFoundationService.getAllRestaurantByStatus(RestaurantStatus.AWAITING);
    }
-----------------------------

        @Override
        public void updateStatus(Long id, StatusEnum status) {
            restaurantFoundationService.updateStatus(id, status);

        }
   @Override
    public List<RestaurantDto> getAllRestaurantByApproved() {
        return restaurantConverter.convertToDtoList(restaurantFoundationService.getAllRestaurantByApproved());
    }


    @Override
    public List<RestaurantDto> getAllRestaurantByAwaiting() {
        return restaurantConverter.convertToDtoList(restaurantFoundationService.getAllRestaurantByAwaiting());

    }


    @Override
    public List<RestaurantDto> getAllRestaurantByStatus(StatusEnum statusEnum) {
        return restaurantConverter.convertToDtoList(restaurantFoundationService.getAllRestaurantByStatus(statusEnum));
    }

     */

}
