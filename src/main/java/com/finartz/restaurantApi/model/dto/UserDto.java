package com.finartz.restaurantApi.model.dto;

import com.finartz.restaurantApi.model.enumeration.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDto extends BaseDto{

    private static final long serialVersionUID = -9190566178036594626L;

    private Role role;
    private String userName;
    private String password;
    private Double latitude;
    private Double longitude;
    private String eMail;

    private List<AddressDto> addresses=new ArrayList<>();

}
