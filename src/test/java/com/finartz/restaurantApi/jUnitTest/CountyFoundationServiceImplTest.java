package com.finartz.restaurantApi.jUnitTest;

import com.finartz.restaurantApi.dao.CountyRepository;
import com.finartz.restaurantApi.model.converter.dto.impl.CountyDTOConverter;
import com.finartz.restaurantApi.model.dto.CityDto;
import com.finartz.restaurantApi.model.dto.CountyDto;
import com.finartz.restaurantApi.model.entity.CountyEntity;
import com.finartz.restaurantApi.service.foundation.impl.CountyFoundationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CountyFoundationServiceImplTest {

    @Mock
    private CountyRepository countyRepository;

    @Mock
    private CountyDTOConverter countyDTOConverter;

    @InjectMocks
    private CountyFoundationServiceImpl countyFoundationService;

    @Test
    public void getByCountyIdTest() {
        CountyDto countyDto = new CountyDto();
        countyDto.setId(1L);

        CountyEntity mockedEntity = getCountyEntity(countyDto);

        when(countyRepository.findCounty(any(Long.class))).thenReturn(mockedEntity);
        when(countyDTOConverter.convertToDto(any(CountyEntity.class))).thenReturn(countyDto);

        CountyDto returnedDto = countyFoundationService.getCounty(countyDto.getId());
        assertNotNull(returnedDto);

        verify(countyRepository).findCounty(countyDto.getId());
        verify(countyDTOConverter).convertToDto(mockedEntity);
    }

    @Test
    public void getByCıtyId() {
        CityDto cityDto = new CityDto();
        cityDto.setId(34L);
        List<CountyDto> counties = new ArrayList<>();

        CountyDto countyDto = new CountyDto();
        countyDto.setId(123L);
        CountyDto countyDto2 = new CountyDto();
        countyDto2.setId(234L);

        counties.add(countyDto);
        counties.add(countyDto2);

        List<CountyEntity> mockedEntityList = getCountyEntities(counties);

        when(countyRepository.findCounties(any(Long.class))).thenReturn(mockedEntityList);
        when(countyDTOConverter.convertToDtoList(anyList())).thenReturn(counties);

        List<CountyDto> returnedCounties = countyFoundationService.getByCity(cityDto.getId());
        assertNotNull(returnedCounties);


        verify(countyRepository).findCounties(cityDto.getId());
        verify(countyDTOConverter).convertToDtoList(mockedEntityList);


    }

    private CountyEntity getCountyEntity(CountyDto countyDto) {
        CountyEntity countyEntity = new CountyEntity();
        countyEntity.setId(countyDto.getId());
        return countyEntity;
    }


    private List<CountyEntity> getCountyEntities(List<CountyDto> countyDtoList) {
        List<CountyEntity> countyEntityList = new ArrayList<>();
        for (CountyDto c : countyDtoList) {
            countyEntityList.add(getCountyEntity(c));
        }
        return countyEntityList;


    }
}
