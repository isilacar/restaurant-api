package com.finartz.restaurantApi.error;

public enum MenuError implements ErrorSymbol{
   MENU_NOT_FOUND("001");

    MenuError(String code) {
        this.code = code;
    }

    private String code;
    public static final String SYMBOL_NAME="menu";

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
