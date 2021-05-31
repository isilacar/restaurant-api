package com.finartz.restaurantApi.model.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class BaseRequest implements Serializable {
    private static final long serialVersionUID = 5401328226362445344L;

    private Long id;
    private LocalDate createDate;
    private LocalDate updateDate;
}
