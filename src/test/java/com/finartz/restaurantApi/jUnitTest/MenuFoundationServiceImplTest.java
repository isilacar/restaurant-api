package com.finartz.restaurantApi.jUnitTest;

import com.finartz.restaurantApi.dao.MenuRepository;
import com.finartz.restaurantApi.model.converter.request.impl.MenuRequestConverter;
import com.finartz.restaurantApi.model.entity.MealEntity;
import com.finartz.restaurantApi.model.entity.MenuEntity;
import com.finartz.restaurantApi.model.entity.RestaurantEntity;
import com.finartz.restaurantApi.model.request.BaseRequest;
import com.finartz.restaurantApi.model.request.CreateMealMenuRequest;
import com.finartz.restaurantApi.model.request.CreateRestaurantMenuRequest;
import com.finartz.restaurantApi.service.foundation.MealFoundationService;
import com.finartz.restaurantApi.service.foundation.RestaurantFoundationService;
import com.finartz.restaurantApi.service.foundation.impl.MenuFoundationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class MenuFoundationServiceImplTest {

    @Mock
    private MenuRepository menuRepository;
    @Mock
    private RestaurantFoundationService restaurantFoundationService;
    @Mock
    private MenuRequestConverter menuRequestConverter;
    @Mock
    private MealFoundationService mealFoundationService;

    @InjectMocks
    private MenuFoundationServiceImpl menuFoundationService;

    @Test
    public void saveRestaurantMenuTest() {
        CreateRestaurantMenuRequest restaurantMenuRequest = new CreateRestaurantMenuRequest(); //restaurantın id sini setliyorum
        restaurantMenuRequest.setRestaurantId(1L);
        restaurantMenuRequest.setId(1L);

        RestaurantEntity mockedRestaurant = getRestaurantEntity(restaurantMenuRequest);
        MenuEntity mockedMenu = getMenuEntity(restaurantMenuRequest);
        mockedMenu.setRestaurant(mockedRestaurant);

        when(restaurantFoundationService.getById(any(Long.class))).thenReturn(mockedRestaurant);
        when(menuRequestConverter.convertToEntity(any(CreateRestaurantMenuRequest.class))).thenReturn(mockedMenu);
        when(menuRepository.saveMenu(any(MenuEntity.class))).thenReturn(mockedMenu);

        MenuEntity menuEntity = menuFoundationService.saveRestaurantMenu(restaurantMenuRequest);
        assertNotNull(menuEntity);
        assertEquals(restaurantMenuRequest.getId(), menuEntity.getId());

        verify(restaurantFoundationService).getById(restaurantMenuRequest.getRestaurantId());
        verify(menuRequestConverter).convertToEntity(restaurantMenuRequest);
        verify(menuRepository).saveMenu(mockedMenu);

    }


    @Test
    public void saveMealTest() {
        CreateMealMenuRequest mealMenuRequest = new CreateMealMenuRequest();
        mealMenuRequest.setMealId(1L);
        mealMenuRequest.setId(1L);

        MealEntity mockedMeal = getMealEntity(mealMenuRequest);
        MenuEntity mockedMenu = getMenuEntity(mealMenuRequest);

       mockedMenu.getMeals().add(mockedMeal);

        when(mealFoundationService.getById(any(Long.class))).thenReturn(mockedMeal);
        when(menuRepository.saveMealToMenu(any(MenuEntity.class))).thenReturn(mockedMenu);
        when(menuRepository.findById(any(Long.class))).thenReturn(mockedMenu);

        MenuEntity menuEntity = menuFoundationService.saveMeal(mealMenuRequest);
        assertNotNull(menuEntity);

        verify(mealFoundationService).getById(mealMenuRequest.getMealId());
        verify(menuRepository).saveMealToMenu(mockedMenu);
        verify(menuRepository).findById(mealMenuRequest.getId());
    }

    @Test
    public void getByIdTest() {
        //CreateRestaurantMenu ile de denendi
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setId(2L);

        when(menuRepository.findById(any(Long.class))).thenReturn(menuEntity);

        MenuEntity menu = menuFoundationService.getById(menuEntity.getId());
        assertNotNull(menu);
        assertEquals(menuEntity.getId(), menu.getId());

        verify(menuRepository).findById(menuEntity.getId());
    }


    private <T extends BaseRequest> MenuEntity getMenuEntity(T t) {
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setId(t.getId());
        menuEntity.setMeals(new ArrayList<>());
        return menuEntity;
    }

    private RestaurantEntity getRestaurantEntity(CreateRestaurantMenuRequest createRestaurantMenuRequest) {
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setId(createRestaurantMenuRequest.getRestaurantId());
        return restaurantEntity;
    }

    private MealEntity getMealEntity(CreateMealMenuRequest createMealMenuRequest) {
        MealEntity mealEntity = new MealEntity();
        mealEntity.setId(createMealMenuRequest.getMealId());
        return mealEntity;
    }
}
