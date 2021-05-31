package com.finartz.restaurantApi.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateMealRequest extends BaseRequest{
    private static final long serialVersionUID = -4400379678072457791L;

    @NotBlank(message = "{mealname.notblank}")
    private String name;

    @Min(value = 1,message = "{mealname.min}")
    private Double price;
}
