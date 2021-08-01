package com.finartz.restaurantApi.error;

public enum UserError implements ErrorSymbol{
    USER_NOT_FOUND("001"); //userla ilgili hatalardan biri userın bulunamaması

    private String code;
    public static final String SYMBOL_NAME="user";

    UserError(String code) {
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

    /*
    burada message.properties dosyamızdaki user.001 hatasının mesajını almaya çalışıyoruz. Bşka bir hatamız olduğunda
    onu da yukrda enum olarak tanımlayıp,kodunu 002 yaparak,getmessage diyerek çağırabilirz. Symbol_namemimiz değişmeyecek ama
    başka hatalar eklendiğinde hata kodları değişecek. o yüzden symbol name i constant olarak tanımladık.
     */

    @Override
    public String getMessage() {
        return SYMBOL_NAME+"."+code;
    }
}
