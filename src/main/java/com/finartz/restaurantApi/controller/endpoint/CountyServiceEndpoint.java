package com.finartz.restaurantApi.controller.endpoint;

public class CountyServiceEndpoint {

    private static final String BASE_URI="/county";

    public class GetCounties{
        public static final String URI=BASE_URI+"/{cityId}";

        public class PathVariable{
            public static final String ID="cityId";
        }
    }
}
