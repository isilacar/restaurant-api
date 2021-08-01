package com.finartz.restaurantApi.model.enumeration;

public enum UserRoleStatus {
    USER,SELLER,ADMIN;

    @Override
    public String toString() {
        return "ROLE_"+this.name();
    }
}
