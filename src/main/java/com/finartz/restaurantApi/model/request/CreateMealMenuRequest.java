package com.finartz.restaurantApi.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateMealMenuRequest extends BaseRequest{
    private static final long serialVersionUID = -2805121506561048395L;

    @NotNull(message = "{mealId.notnull}")
    @Min(value = 1,message = "{mealId.min}")
    private Long mealId;
}
