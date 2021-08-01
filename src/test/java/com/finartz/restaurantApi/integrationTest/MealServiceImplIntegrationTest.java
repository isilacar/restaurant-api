package com.finartz.restaurantApi.integrationTest;

import com.finartz.restaurantApi.model.dto.MealDto;
import com.finartz.restaurantApi.model.request.CreateMealRequest;
import com.finartz.restaurantApi.service.aggregation.MealService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MealServiceImplIntegrationTest {

    private static final Long MEAL_ID=1L;
    private static final String MEAL_NAME = "te";

    @Autowired
    private MealService mealService;

    @Test
    public void saveMealTest(){
        CreateMealRequest mealRequest=new CreateMealRequest();
        mealRequest.setName("testMeal");
        mealRequest.setPrice(10.0);

        MealDto mealDto = mealService.saveMeal(mealRequest);
        assertTrue(mealDto.getId()>0);
        assertNotNull(mealDto.getName());

    }

    @Test
    public void getMealTest(){
        MealDto mealDto = mealService.getMeal(MEAL_ID);
      assertTrue(mealDto.getId()==1L);
      assertNotNull(mealDto);

    }

    @Test
    public void getMealByNameTest(){
        List<MealDto> mealDtoList = mealService.getByMealName(MEAL_NAME);
        assertTrue(!(mealDtoList.isEmpty()));
        assertNotNull(mealDtoList);
    }
}
