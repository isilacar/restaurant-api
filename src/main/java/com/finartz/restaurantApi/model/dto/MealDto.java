package com.finartz.restaurantApi.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealDto extends BaseDto {

    private static final long serialVersionUID = 8731047575673450477L;

    private String name;

    private Double price;

}
