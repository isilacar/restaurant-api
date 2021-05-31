package com.finartz.restaurantApi.model.dto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
public  class BaseDto implements Serializable {

    private static final long serialVersionUID = 2306037042526625521L;

    private Long id;
    private LocalDate createDate;
    private LocalDate updateDate;

}
