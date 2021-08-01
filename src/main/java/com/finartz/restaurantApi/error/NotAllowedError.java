package com.finartz.restaurantApi.error;

public enum NotAllowedError implements ErrorSymbol{
    DO_NOT_HAVE_PERMISSION("001");

    private String code;
    public static final String SYMBOL_NAME="not_allowed";

    NotAllowedError(String code) {
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
