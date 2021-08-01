package com.finartz.restaurantApi.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MenuDto extends BaseDto{

    private static final long serialVersionUID = -8081735843940179144L;
    private List<MealDto> meals=new ArrayList<>();

}

