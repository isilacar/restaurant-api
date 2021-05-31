package com.finartz.restaurantApi.model.request;

import com.finartz.restaurantApi.model.enumeration.StatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRestaurantRequest extends BaseRequest{
    private static final long serialVersionUID = 1827187877048227623L;

    private String name;
    private StatusEnum status = StatusEnum.AWAITING;
    private Long menuId;
    private Double latitude;
    private Double longitude;
}
