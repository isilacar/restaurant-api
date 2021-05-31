package com.finartz.restaurantApi.model.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRestaurantMenuRequest extends BaseRequest {
    private static final long serialVersionUID = 2173861423460034548L;
    private Long restaurantId;
}
