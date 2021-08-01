package com.finartz.restaurantApi.integrationTest;


import com.finartz.restaurantApi.model.dto.CountyDto;
import com.finartz.restaurantApi.service.aggregation.CountyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CountyServiceImplIntegrationTest {

    private final static Long CITY_ID=35L;
    private final static Long COUNTY_ID=868L;

    @Autowired
    private CountyService countyService;

    @Test
    public void getAllCountiesByCityIdTest(){
        List<CountyDto> countyDtoList = countyService.getAllByCityId(CITY_ID);
        assertNotNull(countyDtoList);
        assertTrue(countyDtoList.size()>0);

    }

    @Test
    public void getByIdTest(){
        CountyDto countyDto = countyService.getById(COUNTY_ID);
        assertNotNull(countyDto);
        assertTrue(countyDto.getName().equals("BERGAMA"));
    }
}
