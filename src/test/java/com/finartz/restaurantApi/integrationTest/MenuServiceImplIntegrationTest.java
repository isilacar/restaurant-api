package com.finartz.restaurantApi.integrationTest;

import com.finartz.restaurantApi.model.dto.MealMenuDto;
import com.finartz.restaurantApi.model.dto.MenuDto;
import com.finartz.restaurantApi.model.dto.RestaurantMenuDto;
import com.finartz.restaurantApi.model.request.CreateRestaurantMenuRequest;
import com.finartz.restaurantApi.model.request.MealMenuRequest;
import com.finartz.restaurantApi.service.aggregation.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MenuServiceImplIntegrationTest {

    private static final Long RESTAURANT_ID=8L;
    private static final Long MENU_ID=7L;
    private static final Long MEAL_ID=10L;

    @Autowired
    private MenuService menuService;

    @Test
    public void saveRestaurantMenuTest(){
        CreateRestaurantMenuRequest restaurantMenu=new CreateRestaurantMenuRequest();
        restaurantMenu.setRestaurantId(RESTAURANT_ID);

        RestaurantMenuDto restaurantMenuDto = menuService.saveRestaurantMenu(restaurantMenu);
        assertNotNull(restaurantMenuDto.getRestaurantId());
    }
    @Test
    public void getByIdTest(){
        MenuDto menuDto = menuService.getById(MENU_ID);
      assertTrue(menuDto.getId()>0);
    }

    @Test
    public void saveMealTest(){
        MealMenuRequest mealMenuRequest=new MealMenuRequest();
        mealMenuRequest.setMealId(MEAL_ID);
        mealMenuRequest.setMenuId(MENU_ID);

        MealMenuDto mealMenuDto = menuService.saveMeal(mealMenuRequest);
        assertNotNull(mealMenuDto.getMenuId());
    }
}
