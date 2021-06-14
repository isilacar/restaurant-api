package com.finartz.restaurantApi.model.request;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateRestaurantMenuRequest extends BaseRequest {
    private static final long serialVersionUID = 2173861423460034548L;

    @NotNull(message = "{restaurantId.notnull}")
    private Long restaurantId;
}
