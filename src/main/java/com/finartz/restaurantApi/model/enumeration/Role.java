package com.finartz.restaurantApi.model.enumeration;

public enum Role {
    USER,SELLER,ADMIN;

    @Override
    public String toString() {
        return "ROLE_"+this.name();
    }
}
