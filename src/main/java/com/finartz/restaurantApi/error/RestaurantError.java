package com.finartz.restaurantApi.error;

public enum RestaurantError implements ErrorSymbol{
    RESTAURANT_NOT_FOUND("001");

    private String code;
    public static final String SYMBOL_NAME="restaurant";

    RestaurantError(String code) {
        this.code=code;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getSymbolName() {
        return SYMBOL_NAME;
    }

    @Override
    public String getMessage() {
        return SYMBOL_NAME+"."+code;
    }
}
