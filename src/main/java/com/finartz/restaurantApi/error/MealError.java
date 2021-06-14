package com.finartz.restaurantApi.error;

public enum MealError implements ErrorSymbol{
    MEAL_NOT_FOUND("001");

    MealError(String code) {
        this.code = code;
    }

    private String code;
    public static final String SYMBOL_NAME="meal";

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
