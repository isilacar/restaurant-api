package com.finartz.restaurantApi.controller.endpoint;

public class RestaurantServiceEndpoint {

    private static final String BASE_URI="/restaurant";

    public class Save{
        public static final String URI=BASE_URI;
    }

    public class GetById{
        public static final String URI=BASE_URI+"/{id}";

        public class PathVariable{
            public static final String ID="id";
        }
    }

    public class GetAllNearestRestaurantByStatusApproved{
        public static final String URI=BASE_URI+"/nearest";

    }

    public class UpdateRestaurant{
        public static final String URI=BASE_URI+"/{id}";
        public class PathVariable{
            public static final String ID="id";
        }
    }
    public class GetAllByStatusAwaiting{
        public static final String URI=BASE_URI+"/awaiting";
    }

    public class GetAllByStatusApproved{
        public static final String URI=BASE_URI;
    }

    public class GetRestaurants{
        public static final String URI=BASE_URI+"/all";

    }
}
