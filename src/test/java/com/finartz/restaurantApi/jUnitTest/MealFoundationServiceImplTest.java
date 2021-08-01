
package com.finartz.restaurantApi.jUnitTest;

import com.finartz.restaurantApi.dao.MealRepository;
import com.finartz.restaurantApi.model.converter.dto.impl.MealDTOConverter;
import com.finartz.restaurantApi.model.dto.MealDto;
import com.finartz.restaurantApi.model.entity.MealEntity;
import com.finartz.restaurantApi.service.foundation.impl.MealFoundationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class) //ben spring ayağa kaldırmak istemiyorum dependencylerimi mocklayacağım
public class MealFoundationServiceImplTest {

    @Mock
    private MealRepository mealRepository;

    @Mock
    private MealDTOConverter mealDTOConverter;

    @InjectMocks //constructor karşılığı
    private MealFoundationServiceImpl mealFoundationService; //interface olarak tanımla ve hata aldığını gör

    @Test
    public void saveMealTest() {

        MealDto mealDto = new MealDto();
        mealDto.setName("pizza");

        MealEntity mockEntity = getMealEntity(mealDto);

        //mealrepositorynin save meal methodu çağrıldığında
        // ve parametre olarak meal nesnesi geldiğinde,bana mockentity döneceksin
        when(mealRepository.saveMeal(any(MealEntity.class))).thenReturn(mockEntity);
        when(mealDTOConverter.convertToDto(any(MealEntity.class))).thenReturn(mealDto);
        when(mealDTOConverter.convertToEntity(any(MealDto.class))).thenReturn(mockEntity);

        MealDto returnedMeal = mealFoundationService.saveMeal(mealDto);
        assertEquals(mealDto.getName(), returnedMeal.getName());
        assertNotNull(returnedMeal);

        verify(mealDTOConverter).convertToEntity(mealDto);
        verify(mealRepository).saveMeal(mockEntity);
    }


    @Test
    public void getByIdTest() {

        MealDto mealDto = new MealDto();
        mealDto.setId(2L);

        MealEntity mockedEntity = getMealEntity(mealDto);

        when(mealRepository.findMeal(any(Long.class))).thenReturn(Optional.of(mockedEntity));
        when(mealDTOConverter.convertToDto(any(MealEntity.class))).thenReturn(mealDto);

        MealDto returnedMeal = mealFoundationService.getMeal(mealDto.getId());
        assertEquals((mealDto.getId()), returnedMeal.getId());

        verify(mealRepository).findMeal(mealDto.getId());
        verify(mealDTOConverter).convertToDto(mockedEntity);

    }

    @Test
    public void getByNameTest() {
        List<MealDto> mealDtoList = new ArrayList<>();

        MealDto mealDto = new MealDto();
        mealDto.setName("pizza");
        MealDto mealDto2 = new MealDto();
        mealDto2.setName("pilav");
        MealDto mealDto3 = new MealDto();
        mealDto3.setName("pilaki");
        MealDto mealDto4 = new MealDto();
        mealDto4.setName("makarna");

        mealDtoList.add(mealDto);
        mealDtoList.add(mealDto2);
        mealDtoList.add(mealDto3);
        mealDtoList.add(mealDto4);

        String s= "pi";//mealRequest.getName().substring(0,2);

        List<MealEntity> mockedEntityList = getMealEntityList(mealDtoList, s);

        when(mealRepository.findByName(any(String.class))).thenReturn(mockedEntityList);
        when(mealDTOConverter.convertToDtoList(anyList())).thenReturn(mealDtoList);

        List<MealDto> dtoList = mealFoundationService.getByName(s);
        assertNotNull(dtoList.size());
        assertEquals(mockedEntityList.size(), 3);
        assertEquals(dtoList.get(1).getName(), "pilav");

        verify(mealRepository).findByName(s);

    }

    private List<MealEntity> getMealEntityList(List<MealDto> mealDtoList, String s) {
        List<MealEntity> mealEntities = new ArrayList<>();
        for (MealDto meal : mealDtoList) {
            if(meal.getName().contains(s)){
                mealEntities.add(getMealEntity(meal));
            }
        }
        return mealEntities;

    }


    private MealEntity getMealEntity(MealDto mealDto) {
        MealEntity mealEntity = new MealEntity();
        mealEntity.setName(mealDto.getName());
        mealEntity.setId(mealDto.getId());
        return mealEntity;
    }


}

