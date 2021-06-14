package com.finartz.restaurantApi.model.request;

import com.finartz.restaurantApi.model.enumeration.StatusEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class CreateRestaurantRequest extends BaseRequest{
    private static final long serialVersionUID = 1827187877048227623L;

    @NotBlank(message = "{restaurantName.notBlank}")
    @Size(min = 1,message = "{restaurantName.size}")
    private String name;

    private StatusEnum status = StatusEnum.AWAITING;

    @NotNull(message = "{menuId.notNull}")
    @Min(value = 1,message = "{menuId.size}")
    private Long menuId;

    @DecimalMin(value = "-90.0",message = "{latitude.min}")
    @DecimalMax(value = "90.0",message = "{latitude.max}")
    private Double latitude;

    @DecimalMin(value = "-180.0",message = "{longitude.min}")
    @DecimalMax(value = "180.0",message = "{longitude.max}")
    private Double longitude;
}
