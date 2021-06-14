package com.finartz.restaurantApi.jUnitTest;


import com.finartz.restaurantApi.dao.CityRepository;
import com.finartz.restaurantApi.model.entity.CityEntity;
import com.finartz.restaurantApi.service.foundation.impl.CityFoundationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityFoundationServiceImplTest {

    @Mock
    private CityRepository cityRepository;
    @InjectMocks
    private CityFoundationServiceImpl cityFoundationService;

    @Test
    public void getByIdTest(){
        CityEntity cityEntity=new CityEntity();
        cityEntity.setId(1L);

        when(cityRepository.findById(any(Long.class))).thenReturn(cityEntity);

        CityEntity entity = cityFoundationService.getById(cityEntity.getId());
        assertNotNull(entity);
        assertEquals(cityEntity.getId(),entity.getId());

        verify(cityRepository).findById(cityEntity.getId());
    }

}
