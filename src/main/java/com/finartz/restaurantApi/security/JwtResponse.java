package com.finartz.restaurantApi.security;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = 7258419585545467715L;

    private final String token;
}
