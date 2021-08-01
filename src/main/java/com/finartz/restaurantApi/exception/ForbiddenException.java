package com.finartz.restaurantApi.exception;


import com.finartz.restaurantApi.error.ErrorSymbol;

public class ForbiddenException extends RuntimeException{

    private static final long serialVersionUID = 5568920603814112238L;

    public ForbiddenException(ErrorSymbol error){
        super(error.getMessage());
    }
}