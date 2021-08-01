package com.finartz.restaurantApi.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddressDto extends AddressDto{
    private static final long serialVersionUID = -1265692635105882482L;

    private Long userId;
}
