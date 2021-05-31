package com.finartz.restaurantApi.controller.endpoint;

public class AddressServiceEndpoint {

    private static final String BASE_URI = "/address";

    public class SaveUserAddress {
        public static final String URI = BASE_URI + "/user";
    }

    public class SaveRestaurantAddress {
        public static final String URI = BASE_URI + "/restaurant";
    }

}
