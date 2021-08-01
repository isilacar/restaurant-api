package com.finartz.restaurantApi.integrationTest;

import com.finartz.restaurantApi.model.dto.UserDto;
import com.finartz.restaurantApi.model.enumeration.UserRoleStatus;
import com.finartz.restaurantApi.model.request.CreateUserRequest;
import com.finartz.restaurantApi.security.JwtResponse;
import com.finartz.restaurantApi.service.aggregation.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplIntegrationTest {

    private static final Long USER_ID=19L;
    private static final String TOKEN_USER_NAME="isila";
    private static final String FIND_USER_NAME="acar";
    private static final UserRoleStatus ROLE=UserRoleStatus.USER;
    private static final String USER_NAME="TestUser";
    private static final String EMAIL="test@yahoo.com";
    private static final String PASSWORD="123456";
    private static final Double LATITUDE=49.78979;
    private static final Double LONGITUDE=78.103555;

    @Autowired
    private UserService userService;

    @Test
    public void saveUserTest(){
        CreateUserRequest userRequest=new CreateUserRequest();
        userRequest.setRole(ROLE);
        userRequest.setUserName(USER_NAME);
        userRequest.setEMail(EMAIL);
        userRequest.setPassword(PASSWORD);
        userRequest.setLatitude(LATITUDE);
        userRequest.setLongitude(LONGITUDE);

        UserDto userDto = userService.saveUser(userRequest);
        assertTrue(userDto.getUserName()!=null);
        assertNotNull(userDto.getId());
    }

    @Test
    public void getUserIdTest(){
        UserDto user = userService.getUser(USER_ID);
        assertNotNull(user.getUserName());
    }

    @Test
    public void createTokenTest(){
        CreateUserRequest userRequest=new CreateUserRequest();
        userRequest.setUserName(TOKEN_USER_NAME);
        userRequest.setEMail(EMAIL);
        userRequest.setPassword(PASSWORD);

        JwtResponse token = userService.createToken(userRequest);
        assertNotNull(token);
    }

    @Test
    public void getByUsernameTest(){
        UserDto userDto = userService.getByUsername(FIND_USER_NAME);
        assertNotNull(userDto.getId());
        assertTrue(userDto.getUserName().equals("acar"));
    }

}
