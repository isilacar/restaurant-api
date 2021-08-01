package com.finartz.restaurantApi.error;

public enum JwtError implements ErrorSymbol {
    MALFORMED_EXCEPTION("001"),
    EXPIRED_JWT_EXCEPTION("002");

    private String code;
    public static final String SYMBOL_NAME = "jwt";

    JwtError(String code) {
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
        return SYMBOL_NAME + "." + code;
    }
}
