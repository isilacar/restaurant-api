package com.finartz.restaurantApi.service.aggregation.impl;

import com.finartz.restaurantApi.model.converter.dto.impl.RestaurantDTOConverter;
import com.finartz.restaurantApi.model.dto.RestaurantDto;
import com.finartz.restaurantApi.model.enumeration.StatusEnum;
import com.finartz.restaurantApi.model.request.CreateRestaurantRequest;
import com.finartz.restaurantApi.service.aggregation.RestaurantAggregationService;
import com.finartz.restaurantApi.service.foundation.RestaurantFoundationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class RestaurantAggregationServiceImpl implements RestaurantAggregationService {

    private final RestaurantDTOConverter restaurantConverter;
    private final RestaurantFoundationService restaurantFoundationService;

    public RestaurantAggregationServiceImpl(RestaurantDTOConverter restaurantConverter,
                                            RestaurantFoundationService restaurantFoundationService) {
        this.restaurantConverter = restaurantConverter;
        this.restaurantFoundationService = restaurantFoundationService;
    }

    @Override
    public RestaurantDto saveRestaurant(CreateRestaurantRequest createRestaurantRequest) {
        return restaurantConverter.convertToDto(restaurantFoundationService.saveRestaurant(createRestaurantRequest));
    }

    @Override
    public RestaurantDto getById(Long id) {
        return restaurantConverter.convertToDto(restaurantFoundationService.getById(id));
    }


    @Override
    public List<RestaurantDto> getAllNearestRestaurantByApproved() {
        return restaurantConverter.convertToDtoList(restaurantFoundationService.getAllNearestRestaurantByApproved());
    }

    @Override
    public void updateRestaurant(Long id, CreateRestaurantRequest createRestaurantRequest) {
        restaurantFoundationService.updateRestaurant(id, createRestaurantRequest);
    }


    @Override
    public List<RestaurantDto> getAllRestaurantByApproved() {
        return restaurantConverter.convertToDtoList(restaurantFoundationService.getAllRestaurantByStatus(StatusEnum.APPROVED));
    }

    @Override
    public List<RestaurantDto> getAllRestaurantByAwaiting() {
        return restaurantConverter.convertToDtoList(restaurantFoundationService.getAllRestaurantByStatus(StatusEnum.AWAITING));
    }

    /*
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
