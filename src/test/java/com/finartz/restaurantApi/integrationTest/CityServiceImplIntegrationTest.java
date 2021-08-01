package com.finartz.restaurantApi.integrationTest;

import com.finartz.restaurantApi.model.dto.CityDto;
import com.finartz.restaurantApi.service.aggregation.CityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityServiceImplIntegrationTest {

    private static final Long CITY_ID=35L;
    @Autowired
    private CityService cityService;

    @Test
    public void getCitiesTest(){
        List<CityDto> cities = cityService.getCities();
        assertNotNull(cities);
        assertTrue(cities.size()>0);
    }

    @Test
    public void getCityTest(){
        CityDto city = cityService.getCity(CITY_ID);
        assertNotNull(city);
        assertTrue(city.getName().equals("İzmir"));
    }
}
