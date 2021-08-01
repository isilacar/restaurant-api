package com.finartz.restaurantApi.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantMenuDto extends MenuDto {
    private static final long serialVersionUID = -2717638460610359461L;

    private Long restaurantId;
}
