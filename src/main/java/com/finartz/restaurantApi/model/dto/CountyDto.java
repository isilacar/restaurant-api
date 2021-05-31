package com.finartz.restaurantApi.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountyDto extends BaseDto{

    private static final long serialVersionUID = 2013859424481482129L;

    private CityDto city;

    private String name;
}
