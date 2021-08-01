package com.finartz.restaurantApi.integrationTest;

import com.finartz.restaurantApi.model.dto.RestaurantAddressDto;
import com.finartz.restaurantApi.model.dto.UserAddressDto;
import com.finartz.restaurantApi.model.request.CreateRestaurantAddressRequest;
import com.finartz.restaurantApi.model.request.CreateUserAddressRequest;
import com.finartz.restaurantApi.service.aggregation.AddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceImplIntegrationTest {
    private static final Long CITY_ID = 35L;
    private static final Long COUNTY_ID = 868L;
    private static final Long DISTRICT_ID = 35L;
    private static final Integer ZIP_CODE = 8978;
    private static final String DETAIL = "TEST_DETAIL";
    private static final String TITLE = "TEST-TITLE";
    private static final Long USER_ID = 1L;
    private static final Long RESTAURANT_ID = 7L;

    @Autowired
    private AddressService addressService;

    @Test
    public void saveUserAddressTest() {
        CreateUserAddressRequest userAddressRequest = new CreateUserAddressRequest();
        userAddressRequest.setCityId(CITY_ID);
        userAddressRequest.setCountyId(COUNTY_ID);
        userAddressRequest.setDistrictId(DISTRICT_ID);
        userAddressRequest.setZipCode(ZIP_CODE);
        userAddressRequest.setDetail(DETAIL);
        userAddressRequest.setTitle(TITLE);
        userAddressRequest.setUserId(USER_ID);

        UserAddressDto userAddressDto = addressService.saveUserAddress(userAddressRequest);
        assertNotNull(userAddressDto);
        assertTrue(userAddressDto.getUserId()==1L);
    }

    @Test
    public void saveRestaurantAddressTest(){
        CreateRestaurantAddressRequest restaurantAddressRequest=new CreateRestaurantAddressRequest();
        restaurantAddressRequest.setCityId(CITY_ID);
        restaurantAddressRequest.setCountyId(COUNTY_ID);
        restaurantAddressRequest.setDistrictId(DISTRICT_ID);
        restaurantAddressRequest.setZipCode(ZIP_CODE);
        restaurantAddressRequest.setDetail(DETAIL);
        restaurantAddressRequest.setTitle(TITLE);
        restaurantAddressRequest.setRestaurantId(RESTAURANT_ID);

        RestaurantAddressDto addressDto = addressService.saveRestaurantAddress(restaurantAddressRequest);
        assertNotNull(addressDto);
        assertTrue(addressDto.getRestaurantId()==7L);

    }
}
