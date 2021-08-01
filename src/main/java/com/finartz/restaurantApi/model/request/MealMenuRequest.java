package com.finartz.restaurantApi.model.request;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MealMenuRequest  {

    @NotNull(message = "{mealId.notnull}")
    @Min(value = 1,message = "{mealId.min}")
    private Long mealId;

    @NotNull(message = "{menuId.notnull}")
    @Min(value = 1,message = "{mealId.size}")
    private Long menuId;
}
