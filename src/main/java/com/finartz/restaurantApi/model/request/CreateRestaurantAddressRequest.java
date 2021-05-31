package com.finartz.restaurantApi.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateRestaurantAddressRequest extends BaseRequest{

    private static final long serialVersionUID = 7266425035451835353L;

    @NotNull(message = "{cityId.notnull}")
    @Min(value = 1,message = "{cityId.min}")
    @Max(value = 81,message = "{cityId.max}")
    private Long cityId;

    @NotNull(message = "{countyId.notnull}")
    @Min(value = 1,message = "{countyId.min}")
    @Max(value = 911,message = "{countyId.max}")
    private Long  countyId;

    @NotNull(message = "{districtId.notnull}")
    private Long districtId;

    @NotNull(message = "{zipcode.notnull}")
    @Min(value = 1,message = "{zipcode.min}")
    private Long zipCode;

    @NotBlank(message = "{addressDetail.notblank}")
    private String detail;

    @NotBlank(message = "{addressTitle.notblank}")
    private String title;

    @NotNull(message = "{restaurantId.notnull}")
    @Min(value = 1,message = "{restaurantId.min}")
    private Long restaurantId;

}
