package com.finartz.restaurantApi.controller.endpoint;

public class MenuServiceEndpoint {
    private static final String BASE_URI="/menu";

    public class SaveRestaurantMenu{
        public static final String URI=BASE_URI+"/restaurant";
    }

    public class GetById{
        public static final String URI=BASE_URI+"/{menuId}";

        public class PathVariable{
            public static final String ID="menuId";
        }
    }

    public class SaveMeal{
        public static final String URI=BASE_URI+"/meal";
    }
}
