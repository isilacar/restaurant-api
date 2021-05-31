package com.finartz.restaurantApi.controller.endpoint;

public class MealServiceEndpoint {

    private static final String BASE_URI="/meal";

    public class GetBaseUri{
        public static final String URI=BASE_URI;
    }

    /*
    Ambiguous handler methods mapped for URI, endpointini değiştirmek zorunda kaldım,getByMealname ile getbyId
    biri string biri long alıyordu.getByıd de 1 yolladığımda onun string mi yoksa long mu olacağına karar
    veremiyordu. O yüzden endpointlerini değiştirdim
     */

    public class GetByMealName{
        public static final String URI=BASE_URI+"/name/{mealName}";

        public class PathVariable{
            public static final String MEAL_NAME="mealName";
        }
    }

    public class GetById{
        public static final String URI=BASE_URI+"/{mealId}";

        public class PathVariable{
            public static final String ID="mealId";
        }

    }

}
