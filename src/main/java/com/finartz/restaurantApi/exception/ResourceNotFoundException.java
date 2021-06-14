package com.finartz.restaurantApi.exception;

import com.finartz.restaurantApi.error.ErrorSymbol;

public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 2016606371777340382L;

    public ResourceNotFoundException(ErrorSymbol error) {
       super(error.getMessage());  //symbol_name.code
    }
}
