package com.finartz.restaurantApi.integrationTest;

import com.finartz.restaurantApi.model.converter.dto.impl.UserDTOConverter;
import com.finartz.restaurantApi.model.dto.RestaurantDto;
import com.finartz.restaurantApi.model.dto.UserDto;
import com.finartz.restaurantApi.model.entity.UserEntity;
import com.finartz.restaurantApi.model.enumeration.RestaurantStatus;
import com.finartz.restaurantApi.model.enumeration.UserRoleStatus;
import com.finartz.restaurantApi.model.request.CreateRestaurantRequest;
import com.finartz.restaurantApi.security.TokenManager;
import com.finartz.restaurantApi.service.aggregation.RestaurantService;
import com.finartz.restaurantApi.service.aggregation.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RestaurantServiceImplIntegrationTest {

    private static final RestaurantStatus AWAITING = RestaurantStatus.AWAITING;
    private static final RestaurantStatus REJECTED = RestaurantStatus.REJECTED;
    private static final RestaurantStatus APPROVED = RestaurantStatus.APPROVED;
    private static final String NAME = "TestRestaurant";
    private static final Double LATITUDE = 40.976828;
    private static final Double LONGITUDE = 29.103555;
    private static final Long RESTAURANT_ID = 8L;
    private static final String BEARER = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhY2FyMiIsImV4cCI6MTYyNTY0OTI5OSwiaWF0IjoxNjI1NjQ4Mzk5LCJhdXRob3JpdGllcyI6IlJPTEVfQURNSU4ifQ.585LjDYKjXOt53X9qmQagQcKsnApRY1nYzrXVYPa_Cm26Efhz0tR65nne_0Asw3rgoJUYdJk8NBo360WOwNMUg";

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDTOConverter userDTOConverter;


    @Test
    public void saveRestaurantTest() {
        CreateRestaurantRequest restaurantRequest = new CreateRestaurantRequest();
        restaurantRequest.setName(NAME);
        restaurantRequest.setLatitude(LATITUDE);
        restaurantRequest.setLongitude(LONGITUDE);

        RestaurantDto restaurantDto = restaurantService.saveRestaurant(restaurantRequest);
        assertNotNull(restaurantDto.getId());

    }

    @Test
    public void getByIdTest() {
        RestaurantDto restaurantDto = restaurantService.getById(RESTAURANT_ID);
        assertNotNull(restaurantDto.getId());
        assertTrue(restaurantDto.getName().equals("Çiçek"));

    }

    @Test
    public void updateRestaurantTest() {
        CreateRestaurantRequest restaurantRequest = new CreateRestaurantRequest();
        restaurantRequest.setName("UpdatedTest");
        restaurantRequest.setStatus(RestaurantStatus.APPROVED);

        restaurantService.updateRestaurant(9L, restaurantRequest);
        RestaurantDto restaurantDto = restaurantService.getById(9L);
        assertEquals(restaurantDto.getName(),"UpdatedTest");

    }

    @Test
    public void getRestaurantsTest() {
        //  String username = tokenManager.getUsernameFromToken(token);
        String tokenHeader = BEARER;
        String token = tokenHeader.substring(7);
        String username = tokenManager.getUsernameFromToken(token);
        UserDto userDto = userService.getByUsername(username);
        UserEntity userEntity = userDTOConverter.convertToEntity(userDto);
        UserRoleStatus role = userEntity.getRole();
        List<RestaurantDto> restaurants = restaurantService.getRestaurants(APPROVED);
        assertNotNull(restaurants);

    }
}
