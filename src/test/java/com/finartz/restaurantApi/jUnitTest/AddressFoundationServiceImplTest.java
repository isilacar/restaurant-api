package com.finartz.restaurantApi.jUnitTest;


import com.finartz.restaurantApi.dao.AddressRepository;
import com.finartz.restaurantApi.model.converter.dto.impl.*;
import com.finartz.restaurantApi.model.dto.*;
import com.finartz.restaurantApi.model.entity.*;
import com.finartz.restaurantApi.service.foundation.CityFoundationService;
import com.finartz.restaurantApi.service.foundation.CountyFoundationService;
import com.finartz.restaurantApi.service.foundation.RestaurantFoundationService;
import com.finartz.restaurantApi.service.foundation.UserFoundationService;
import com.finartz.restaurantApi.service.foundation.impl.AddressFoundationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddressFoundationServiceImplTest {

    @Mock
    private AddressRepository addressRepository;
    @Mock
    private UserFoundationService userFoundationService;
    @Mock
    private CityFoundationService cityFoundationService;
    @Mock
    private CountyFoundationService countyFoundationService;
    @Mock
    private RestaurantFoundationService restaurantFoundationService;
    @Mock
    private CityDTOConverter cityDTOConverter;
    @Mock
    private CountyDTOConverter countyDTOConverter;
    @Mock
    private UserAddressDtoConverter userAddressDtoConverter;
    @Mock
    private RestaurantAddressDtoConverter restaurantAddressDtoConverter;
    @Mock
    private UserDTOConverter userDTOConverter;
    @Mock
    private AddressDTOConverter addressDTOConverter;
    @Mock
    private RestaurantDTOConverter restaurantDTOConverter;
    @InjectMocks
    private AddressFoundationServiceImpl addressFoundationService;

    @Test
    public void saveUserAddress(){
        UserDto userDto=new UserDto();
        userDto.setId(1L);
        CityDto cityDto=new CityDto();
        cityDto.setId(1L);
        CountyDto countyDto=new CountyDto();
       countyDto.setId(1L);

        UserAddressDto addressDto=new UserAddressDto();
        addressDto.setId(1L);
        addressDto.setUserId(userDto.getId());
        addressDto.setCounty(countyDto);
        addressDto.setCity(cityDto);

        AddressEntity mockedAddressEntity = getAddressEntity(addressDto);
        UserEntity mockedUserEntity = getUserEntity(userDto);
        CityEntity mockedCity=getCityEntity(cityDto);
        CountyEntity mockedCounty = getCountyEntity(countyDto);
        mockedAddressEntity.setUserEntity(mockedUserEntity);
        mockedAddressEntity.setCityEntity(mockedCity);
        mockedAddressEntity.setCountyEntity(mockedCounty);

        when(userFoundationService.getUser(any(Long.class))).thenReturn(userDto);
        when(addressRepository.saveAddress(any(AddressEntity.class))).thenReturn(mockedAddressEntity);
        when(userDTOConverter.convertToEntity(any(UserDto.class))).thenReturn(mockedUserEntity);
        when(addressDTOConverter.convertToEntity(any(UserAddressDto.class))).thenReturn(mockedAddressEntity);
       when(userAddressDtoConverter.convertToDto(any(AddressEntity.class))).thenReturn(addressDto);

        UserAddressDto returnedDto = addressFoundationService.saveUserAddress(addressDto);
       assertNotNull(returnedDto);

       verify(addressRepository).saveAddress(mockedAddressEntity);
       verify(userDTOConverter).convertToEntity(userDto);
       verify(userFoundationService).getUser(userDto.getId());
       verify(addressDTOConverter).convertToEntity(addressDto);
       verify(userAddressDtoConverter).convertToDto(mockedAddressEntity);

    }

    @Test
    public void saveRestaurantAddress(){
        RestaurantDto restaurantDto=new RestaurantDto();
        restaurantDto.setId(1L);
        CityDto cityDto=new CityDto();
        cityDto.setId(1L);
        CountyDto countyDto=new CountyDto();
        countyDto.setId(1L);

        RestaurantAddressDto restaurantAddressDto=new RestaurantAddressDto();
        restaurantAddressDto.setId(1L);
        restaurantAddressDto.setRestaurantId(restaurantDto.getId());
        restaurantAddressDto.setCounty(countyDto);
        restaurantAddressDto.setCity(cityDto);

        AddressEntity mockedAddress = getAddressEntity(restaurantAddressDto);
        RestaurantEntity mockedRestaurant = getRestaurantEntity(restaurantDto);
        CityEntity mockedCity = getCityEntity(cityDto);
        CountyEntity mockedCounty = getCountyEntity(countyDto);
        mockedAddress.setRestaurantEntity(mockedRestaurant);
        mockedAddress.setCityEntity(mockedCity);
        mockedAddress.setCountyEntity(mockedCounty);
        when(restaurantFoundationService.getRestaurant(any(Long.class))).thenReturn(restaurantDto);
        when(addressRepository.saveAddress(any(AddressEntity.class))).thenReturn(mockedAddress);
        when(restaurantDTOConverter.convertToEntity(any(RestaurantDto.class))).thenReturn(mockedRestaurant);
        when(addressDTOConverter.convertToEntity(any(RestaurantAddressDto.class))).thenReturn(mockedAddress);
        when(restaurantAddressDtoConverter.convertToDto(any(AddressEntity.class))).thenReturn(restaurantAddressDto);

        RestaurantAddressDto returnedDto = addressFoundationService.saveRestaurantAddress(restaurantAddressDto);
        assertNotNull(returnedDto);

        verify(restaurantFoundationService).getRestaurant(restaurantDto.getId());
        verify(addressRepository).saveAddress(mockedAddress);
        verify(restaurantDTOConverter).convertToEntity(restaurantDto);
        verify(addressDTOConverter).convertToEntity(restaurantAddressDto);
        verify(restaurantAddressDtoConverter).convertToDto(mockedAddress);

    }


    private RestaurantEntity getRestaurantEntity(RestaurantDto restaurantDto) {
        RestaurantEntity restaurantEntity=new RestaurantEntity();
        restaurantEntity.setId(restaurantDto.getId());
        return restaurantEntity;
    }

    private UserEntity getUserEntity(UserDto userDto) {
        UserEntity userEntity=new UserEntity();
        userEntity.setId(userDto.getId());
        return userEntity;
    }

    private <T extends BaseDto> AddressEntity getAddressEntity(T t) {
        AddressEntity addressEntity=new AddressEntity();
        addressEntity.setId(t.getId());
        return addressEntity;
    }
    private CityEntity getCityEntity(CityDto cityDto) {
        CityEntity cityEntity=new CityEntity();
        cityEntity.setId(cityDto.getId());
        return cityEntity;
    }
    private CountyEntity getCountyEntity(CountyDto countyDto) {
        CountyEntity countyEntity=new CountyEntity();
        countyEntity.setId(countyDto.getId());
        return countyEntity;
    }

}
