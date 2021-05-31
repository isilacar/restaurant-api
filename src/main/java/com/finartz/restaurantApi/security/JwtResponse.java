package com.finartz.restaurantApi.security;


import lombok.Getter;

import java.io.Serializable;

@Getter
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = 7258419585545467715L;

    private final String token;
    public JwtResponse(String token) {
        this.token = token;
    }
}
