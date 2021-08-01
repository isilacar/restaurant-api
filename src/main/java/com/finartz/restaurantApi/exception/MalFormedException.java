package com.finartz.restaurantApi.exception;

import com.finartz.restaurantApi.error.ErrorSymbol;

public class MalFormedException extends RuntimeException{
    private static final long serialVersionUID = 1701661706891793347L;

    public MalFormedException(ErrorSymbol error){
        super(error.getMessage());
    }
}
