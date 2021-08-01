package com.finartz.restaurantApi.error;

public enum InternalServerError implements ErrorSymbol {
    INTERNAL_SERVER_ERROR("001");

    private String code;
    public static final String SYMBOL_NAME = "general.error";

    InternalServerError(String code) {
        this.code = code;
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
