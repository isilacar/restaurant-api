package com.finartz.restaurantApi.exception;

import com.finartz.restaurantApi.error.ErrorSymbol;

public class MyJwtException extends RuntimeException{

    private static final long serialVersionUID = -8748171744673578754L;

    public MyJwtException(ErrorSymbol error){
        super(error.getMessage());
    }

}

