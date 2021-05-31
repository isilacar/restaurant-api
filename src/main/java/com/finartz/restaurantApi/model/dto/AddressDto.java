package com.finartz.restaurantApi.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto extends BaseDto {

    private static final long serialVersionUID = -5315160993450345581L;

    private CityDto city;

    private CountyDto county;

    private Long districtId;

    private int zipCode;

    private String detail;

    private String title;

}
