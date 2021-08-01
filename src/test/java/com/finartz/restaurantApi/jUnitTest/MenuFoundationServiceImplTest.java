package com.finartz.restaurantApi.jUnitTest;

import com.finartz.restaurantApi.dao.MenuRepository;
import com.finartz.restaurantApi.model.converter.dto.impl.*;
import com.finartz.restaurantApi.model.dto.*;
import com.finartz.restaurantApi.model.entity.MealEntity;
import com.finartz.restaurantApi.model.entity.MenuEntity;
import com.finartz.restaurantApi.model.entity.RestaurantEntity;
import com.finartz.restaurantApi.service.foundation.MealFoundationService;
import com.finartz.restaurantApi.service.foundation.RestaurantFoundationService;
import com.finartz.restaurantApi.service.foundation.impl.MenuFoundationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Optional;

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
    private MealDTOConverter mealDTOConverter;
    @Mock
    private MenuDTOConverter menuDTOConverter;
    @Mock
    private RestaurantDTOConverter restaurantDTOConverter;
    @Mock
    private RestaurantMenuDtoConverter restaurantMenuDtoConverter;
    @Mock
    private MealMenuDtoConverter mealMenuDtoConverter;
    @Mock
    private MealFoundationService mealFoundationService;

    @InjectMocks
    private MenuFoundationServiceImpl menuFoundationService;

    @Test
    public void saveRestaurantMenuTest() {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(1L);
        MenuDto menuDto = new MenuDto();
        menuDto.setId(1L);

        RestaurantMenuDto restaurantMenuDto = new RestaurantMenuDto();
        restaurantMenuDto.setRestaurantId(restaurantDto.getId());
        restaurantMenuDto.setId(menuDto.getId());

        RestaurantEntity mockedRestaurant = getRestaurantEntity(restaurantDto);
        MenuEntity mockedMenu = getMenuEntity(menuDto);
        mockedMenu.setRestaurant(mockedRestaurant);

        when(menuDTOConverter.convertToEntity(any(MenuDto.class))).thenReturn(mockedMenu);
        when(restaurantDTOConverter.convertToEntity(any(RestaurantDto.class))).thenReturn(mockedRestaurant);
        when(restaurantFoundationService.getRestaurant(any(Long.class))).thenReturn(restaurantDto);
        when(menuRepository.saveMenu(any(MenuEntity.class))).thenReturn(mockedMenu);
        when(restaurantMenuDtoConverter.convertToDto(any(MenuEntity.class))).thenReturn(restaurantMenuDto);

        RestaurantMenuDto returnedValue = menuFoundationService.saveRestaurantMenu(restaurantMenuDto);
        assertNotNull(returnedValue);

        verify(menuDTOConverter).convertToEntity(restaurantMenuDto);
        verify(restaurantDTOConverter).convertToEntity(restaurantDto);
        verify(restaurantFoundationService).getRestaurant(restaurantDto.getId());
        verify(menuRepository).saveMenu(mockedMenu);
        verify(restaurantMenuDtoConverter).convertToDto(mockedMenu);

    }

    @Test
    public void saveMealTest() {
        MealDto mealDto = new MealDto();
        mealDto.setId(1L);
        MenuDto menuDto = new MenuDto();
        menuDto.setId(1L);

        MealMenuDto mealMenuDto = new MealMenuDto();
        mealMenuDto.setMealId(mealDto.getId());
        mealMenuDto.setMenuId(menuDto.getId());

        MealEntity mockedMeal = getMealEntity(mealDto);
        MenuEntity mockedMenu = getMenuEntity(menuDto);
        mockedMenu.getMeals().add(mockedMeal);

        when(mealFoundationService.getMeal(any(Long.class))).thenReturn(mealDto);
        when(mealDTOConverter.convertToEntity(any(MealDto.class))).thenReturn(mockedMeal);
        when(menuRepository.findMenu(any(Long.class))).thenReturn(Optional.of(mockedMenu));
        when(menuRepository.saveMealToMenu(any(MenuEntity.class))).thenReturn(mockedMenu);
        when(mealMenuDtoConverter.convertToDto(any(MenuEntity.class))).thenReturn(mealMenuDto);

        MealMenuDto returnedValue = menuFoundationService.saveMeal(mealMenuDto);
        assertNotNull(returnedValue);

        verify(mealFoundationService).getMeal(mealDto.getId());
        verify(mealDTOConverter).convertToEntity(mealDto);
        verify(menuRepository).findMenu(menuDto.getId());
        verify(menuRepository).saveMealToMenu(mockedMenu);
        verify(mealMenuDtoConverter).convertToDto(mockedMenu);

    }


    @Test
    public void getByIdTest() {

        MenuDto menuDto = new MenuDto();
        menuDto.setId(1L);

        MenuEntity mockedMenu = getMenuEntity(menuDto);

        when(menuRepository.findMenu(any(Long.class))).thenReturn(Optional.of(mockedMenu));
        when(menuDTOConverter.convertToDto(any(MenuEntity.class))).thenReturn(menuDto);

        MenuDto returnedMenu = menuFoundationService.getMenu(menuDto.getId());
        assertNotNull(returnedMenu);
        assertEquals(menuDto.getId(),returnedMenu.getId());

        verify(menuRepository).findMenu(menuDto.getId());
        verify(menuDTOConverter).convertToDto(mockedMenu);

    }


    private <T extends BaseDto> MenuEntity getMenuEntity(T t) {
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setId(t.getId());
        menuEntity.setMeals(new ArrayList<>());
        return menuEntity;
    }

    private RestaurantEntity getRestaurantEntity(RestaurantDto restaurantDto) {
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setId(restaurantDto.getId());
        return restaurantEntity;
    }

    private MealEntity getMealEntity(MealDto mealDto) {
        MealEntity mealEntity = new MealEntity();
        mealEntity.setId(mealDto.getId());
        return mealEntity;
    }
}
