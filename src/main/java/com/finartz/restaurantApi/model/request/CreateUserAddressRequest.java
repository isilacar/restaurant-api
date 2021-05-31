package com.finartz.restaurantApi.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserAddressRequest extends BaseRequest{
    private static final long serialVersionUID = 2790911624438334547L;

    private Long  cityId;
    private Long countyId;
    private Long districtId;
    private int zipCode;
    private String detail;
    private String title;
    private Long userId;
}
