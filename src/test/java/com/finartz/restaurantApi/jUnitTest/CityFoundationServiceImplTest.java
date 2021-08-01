package com.finartz.restaurantApi.jUnitTest;


import com.finartz.restaurantApi.dao.CityRepository;
import com.finartz.restaurantApi.model.converter.dto.impl.CityDTOConverter;
import com.finartz.restaurantApi.model.dto.CityDto;
import com.finartz.restaurantApi.model.entity.CityEntity;
import com.finartz.restaurantApi.service.foundation.impl.CityFoundationServiceImpl;
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
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityFoundationServiceImplTest {

    @Mock
    private CityRepository cityRepository;
    @Mock
    private CityDTOConverter cityDTOConverter;
    @InjectMocks
    private CityFoundationServiceImpl cityFoundationService;

    @Test
    public void getByIdTest() {
        CityDto cityDto = new CityDto();
        cityDto.setId(1L);

        CityEntity mockedEntity = getEntity(cityDto);

        when(cityRepository.findCity(any(Long.class))).thenReturn(mockedEntity);
        when(cityDTOConverter.convertToDto(any(CityEntity.class))).thenReturn(cityDto);

        CityDto returnedCity = cityFoundationService.getCity(cityDto.getId());
        assertNotNull(returnedCity);
        assertEquals(cityDto.getId(), returnedCity.getId());

        verify(cityRepository).findCity(returnedCity.getId());
        verify(cityDTOConverter).convertToDto(mockedEntity);
    }

    @Test
    public void getAllCities() {
        List<CityDto> cityDtos = new ArrayList<>();

        CityDto cityDto = new CityDto();
        cityDto.setId(1L);
        CityDto cityDto2 = new CityDto();
        cityDto2.setId(1L);
        CityDto cityDto3 = new CityDto();
        cityDto3.setId(1L);

        cityDtos.add(cityDto);
        cityDtos.add(cityDto2);
        cityDtos.add(cityDto3);

        List<CityEntity> mockedEntity = getCities(cityDtos);

        when(cityRepository.findCitites()).thenReturn(mockedEntity);
        when(cityDTOConverter.convertToDtoList(anyList())).thenReturn(cityDtos);

        List<CityDto> returnedDto = cityFoundationService.getAll();
        assertNotNull(returnedDto);
        assertEquals(returnedDto.size(), cityDtos.size());

        verify(cityRepository).findCitites();
        verify(cityDTOConverter).convertToDtoList(mockedEntity);


    }

    private CityEntity getEntity(CityDto cityDto) {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setId(cityDto.getId());
        return cityEntity;
    }

    private List<CityEntity> getCities(List<CityDto> cityDtoList) {
        List<CityEntity> cities = new ArrayList<>();
        for (CityDto c : cityDtoList) {
            cities.add(getEntity(c));
        }
        return cities;
    }

}

