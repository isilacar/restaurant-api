package com.finartz.restaurantApi.error;

public enum BadCredentialError implements ErrorSymbol{
    BAD_CREDENTIAL_ERROR("001");

    private String code;
    public static final String SYMBOL_NAME="bad_credential";

    BadCredentialError(String code) {
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
