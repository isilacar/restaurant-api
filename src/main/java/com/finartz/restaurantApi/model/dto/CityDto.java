package com.finartz.restaurantApi.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityDto extends BaseDto {

    private static final long serialVersionUID = -134744611201390344L;

    private String name;

    private int licensePlate;

}
