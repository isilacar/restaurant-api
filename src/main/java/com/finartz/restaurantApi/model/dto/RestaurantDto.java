package com.finartz.restaurantApi.model.dto;

import com.finartz.restaurantApi.model.enumeration.RestaurantStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RestaurantDto extends BaseDto {

    private static final long serialVersionUID = -6215547808946308597L;

    private String name;
    private RestaurantStatus status = RestaurantStatus.AWAITING;
    private MenuDto menu;
    private Double latitude;
    private Double longitude;
    private List<AddressDto> addresses = new ArrayList<>();

}
