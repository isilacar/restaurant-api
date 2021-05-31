package com.finartz.restaurantApi.security;

import com.finartz.restaurantApi.model.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;


public class MyUserDetails implements UserDetails {

    private static final long serialVersionUID = -8699532776992621244L;

    private GrantedAuthority authority;
    private String userName;
    private String password;
    private Double latitude;
    private Double longitude;


    public MyUserDetails(UserEntity user) {
       this.authority=new SimpleGrantedAuthority(user.getRole().toString());
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.latitude = user.getLatitude();
        this.longitude = user.getLongitude();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
