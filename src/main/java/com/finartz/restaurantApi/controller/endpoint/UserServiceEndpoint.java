package com.finartz.restaurantApi.controller.endpoint;

public class UserServiceEndpoint {

    private static final String BASE_URI="/user";

    public class CreateToken{
        public static final String URI="/login";
    }
    public class SaveUser{
        public static final String URI=BASE_URI;
    }
    public class GetUser{
        public static final String URI=BASE_URI+"/{id}";

        public class PathVariable{
            public static final String ID="id";
        }
    }
}
