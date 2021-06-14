package com.finartz.restaurantApi.jUnitTest;

import com.finartz.restaurantApi.dao.MealRepository;
import com.finartz.restaurantApi.model.converter.request.impl.MealRequestConverter;
import com.finartz.restaurantApi.model.entity.MealEntity;
import com.finartz.restaurantApi.model.request.CreateMealRequest;
import com.finartz.restaurantApi.service.foundation.impl.MealFoundationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class) //ben spring ayağa kaldırmak istemiyorum dependencylerimi mocklayacağım
public class MealFoundationServiceImplTest {

    @Mock
    private MealRepository mealRepository;

    @Mock
    private MealRequestConverter mealRequestConverter;

    @InjectMocks //constrcutor karşılığı
    private MealFoundationServiceImpl mealFoundationService; //interface olarak tanımla ve hata aldığını gör

    @Test
    public void saveMealTest() {

        CreateMealRequest mealRequest = new CreateMealRequest();
        mealRequest.setName("pizza");

        MealEntity mockEntity = getMealEntity(mealRequest);

        //mealrepositorynin save meal methodu çağrıldığında
        // ve parametre olarak meal nesnesi geldiğinde,bana mockentity döneceksin
        when(mealRepository.saveMeal(any(MealEntity.class))).thenReturn(mockEntity);
        when(mealRequestConverter.convertToEntity(any(CreateMealRequest.class))).thenReturn(mockEntity);

        MealEntity returnedMeal = mealFoundationService.saveMeal(mealRequest);
        assertEquals(mealRequest.getName(), returnedMeal.getName());
        assertNotNull(returnedMeal);


        verify(mealRequestConverter).convertToEntity(mealRequest);
        verify(mealRepository).saveMeal(mockEntity);
    }


    @Test
    public void getByIdTest() {

        //BURADA MEALENTİTY ALABİLİRMİYİM, İD İLE İLGİLENDİĞİM İÇİN

        CreateMealRequest mealRequest = new CreateMealRequest();
        mealRequest.setId(2L);

        MealEntity mockedEntity = getMealEntity(mealRequest);

        when(mealRepository.findById(any(Long.class))).thenReturn(mockedEntity);

        MealEntity returnedMeal = mealFoundationService.getById(mealRequest.getId());
        assertEquals((mealRequest.getId()), returnedMeal.getId());

        verify(mealRepository).findById(mealRequest.getId());

    }

    @Test
    public void getByNameTest() {
        List<CreateMealRequest> mealRequestList = new ArrayList<>();

        CreateMealRequest mealRequest = new CreateMealRequest();
        mealRequest.setName("pizza");
        CreateMealRequest mealRequest2 = new CreateMealRequest();
        mealRequest2.setName("pilav");
        CreateMealRequest mealRequest3 = new CreateMealRequest();
        mealRequest3.setName("pilaki");
        CreateMealRequest mealRequest4 = new CreateMealRequest();
        mealRequest4.setName("makarna");

        /*
        CreateMealRequest mealRequest3 = new CreateMealRequest();
        mealRequest3.setName("pide");
         mealRequestList.add(mealRequest3);
         */
        mealRequestList.add(mealRequest);
        mealRequestList.add(mealRequest2);
        mealRequestList.add(mealRequest3);
        mealRequestList.add(mealRequest4);

        String s= "pi";//mealRequest.getName().substring(0,2);

        List<MealEntity> mockedEntityList = getMealEntityList(mealRequestList, s);

        when(mealRepository.findByName(any(String.class))).thenReturn(mockedEntityList);

        List<MealEntity> entityList = mealFoundationService.getByName(s);
        assertNotNull(entityList.size());
        assertEquals(mockedEntityList.size(), 3);
        assertEquals(entityList.get(1).getName(), "pilav");

        verify(mealRepository).findByName(s);

    }

    private List<MealEntity> getMealEntityList(List<CreateMealRequest> mealRequestList, String s) {
        List<MealEntity> mealEntities = new ArrayList<>();
        for (CreateMealRequest c : mealRequestList) {
            /*
            if (c.getName().startsWith(s)) {
                mealEntities.add(getMealEntity(c));
            }*/
            if(c.getName().contains(s)){
                mealEntities.add(getMealEntity(c));
            }
        }
        return mealEntities;

    }


    private MealEntity getMealEntity(CreateMealRequest mealRequest) {
        MealEntity mealEntity = new MealEntity();
        mealEntity.setName(mealRequest.getName());
        mealEntity.setId(mealRequest.getId());
        return mealEntity;
    }


}
