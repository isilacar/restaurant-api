package com.finartz.restaurantApi.model.request;

import com.finartz.restaurantApi.model.enumeration.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class CreateUserRequest extends BaseRequest{
    private static final long serialVersionUID = -6087590188257097786L;
    public static final String EMAIL_REGEXP = "^[A-Za-z0-9+_-]+(?:\\.[a-zA-Z0-9]+)*@[A-Za-z]+\\.[A-Za-z]+$";
    public static final String PASSWORD_REGEXP = "^[a-zA-Z0-9]{6}";

    private Role role;

    @NotNull(message = "{username.notnull}")       //null değer alamaz
    @NotEmpty(message = "{username.notempty}")  //null,empty olamaz,value length 0 dan büyük olmalı,null,"" gibi olamaz
    @NotBlank(message = "{username.notblank}")     /*
                                            null,empty olamaz,içeri gelen değer trim edilir,ve herbir değerin uzunluğu
                                            0 dan büyük olmalıdır.Bu durumda ben null,empty olmasını istemediğim için
                                            notblank kullanmam benim için daha avantajlı*/


    @Size(min = 3,max = 35,message = "{username.size}")
    private String userName;

    /*
    email@xx.com,sonunda mutlaka .com olmasını istersem
    "^[A-Za-z0-9+_-]+(?:\\.[a-zA-Z0-9]+)*@[A-Za-z]+(.com)$"
     */

    //properties filedan error mesajları tanımlayıp çekmeye bak.
    //.dan sonra bla bla gelsin
    @Pattern(regexp = EMAIL_REGEXP,
            message = "{email.pattern}")
    @NotNull(message = "{email.notnull}")
    private String eMail;

    @NotBlank(message = "{password.notblank}")
    @Pattern(regexp= PASSWORD_REGEXP,message="{password.pattern}")//şifremiz harf sayı içerebilir,6 karakter olmalı
    private String password;

    @DecimalMin(value = "-90.0",message = "{latitude.min}")
    @DecimalMax(value = "90.0",message = "{latitude.max}")
    private Double latitude;

    @DecimalMin(value = "-180.0",message = "{longitude.min}")
    @DecimalMax(value = "180.0",message = "{longitude.max}")
    private Double longitude;
}
