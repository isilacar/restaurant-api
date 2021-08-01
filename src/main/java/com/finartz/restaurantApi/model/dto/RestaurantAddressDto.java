package com.finartz.restaurantApi.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantAddressDto extends AddressDto{

    private static final long serialVersionUID = 5358422941020069813L;

    private Long restaurantId;
}
