package com.finartz.restaurantApi.jUnitTest;

import com.finartz.restaurantApi.dao.CountyRepository;
import com.finartz.restaurantApi.model.entity.CountyEntity;
import com.finartz.restaurantApi.service.foundation.impl.CountyFoundationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CountyFoundationServiceImplTest {

    @Mock
    private CountyRepository countyRepository;

    @InjectMocks
    private CountyFoundationServiceImpl countyFoundationService;

    @Test
    public void getByIdTest(){
        CountyEntity countyEntity=new CountyEntity();
        countyEntity.setId(1L);

        when(countyRepository.findById(any(Long.class))).thenReturn(countyEntity);

        CountyEntity entity = countyFoundationService.getById(countyEntity.getId());
        assertNotNull(entity);

        verify(countyRepository).findById(countyEntity.getId());
    }


}
