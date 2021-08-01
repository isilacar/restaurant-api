package com.finartz.restaurantApi.exception;

import com.finartz.restaurantApi.error.ErrorSymbol;

public class TokenExpiredException extends RuntimeException{
    private static final long serialVersionUID = -1143302022527981201L;

    public TokenExpiredException(ErrorSymbol error){
        super(error.getMessage());
    }
}
