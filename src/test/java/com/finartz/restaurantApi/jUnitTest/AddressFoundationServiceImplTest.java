package com.finartz.restaurantApi.jUnitTest;


import com.finartz.restaurantApi.dao.AddressRepository;
import com.finartz.restaurantApi.model.converter.request.impl.AddressRequestConverter;
import com.finartz.restaurantApi.model.entity.AddressEntity;
import com.finartz.restaurantApi.model.entity.RestaurantEntity;
import com.finartz.restaurantApi.model.entity.UserEntity;
import com.finartz.restaurantApi.model.request.BaseRequest;
import com.finartz.restaurantApi.model.request.CreateRestaurantAddressRequest;
import com.finartz.restaurantApi.model.request.CreateUserAddressRequest;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddressFoundationServiceImplTest {

    @Mock
    private AddressRepository addressRepository;
    @Mock
    private AddressRequestConverter addressRequestConverter;
    @Mock
    private UserFoundationService userFoundationService;
    @Mock
    private CityFoundationService cityFoundationService;
    @Mock
    private CountyFoundationService countyFoundationService;
    @Mock
    private RestaurantFoundationService restaurantFoundationService;
    @InjectMocks
    private AddressFoundationServiceImpl addressFoundationService;

    @Test
    public void saveUserAddress(){
        CreateUserAddressRequest userAddressRequest=new CreateUserAddressRequest();
     //   userAddressRequest.setTitle("Ev Adresi");
        userAddressRequest.setUserId(1L);
        userAddressRequest.setId(1L);

        AddressEntity mockedAddress = getAddressEntity(userAddressRequest);
        UserEntity mockedUser = getUserEntity(userAddressRequest);
        mockedAddress.setUserEntity(mockedUser);

        when(userFoundationService.getById(any(Long.class))).thenReturn(mockedUser);
        when(addressRepository.saveAddress(any(AddressEntity.class))).thenReturn(mockedAddress);
        when(addressRequestConverter.convert(any(CreateUserAddressRequest.class))).thenReturn(mockedAddress);


        AddressEntity addressEntity = addressFoundationService.saveUserAddress(userAddressRequest);

        assertNotNull(addressEntity);
        assertEquals(userAddressRequest.getUserId(),addressEntity.getUserEntity().getId());
        assertEquals(userAddressRequest.getId(),addressEntity.getId());

        verify(addressRepository).saveAddress(mockedAddress);
        verify(addressRequestConverter).convert(userAddressRequest);
        verify(userFoundationService).getById(userAddressRequest.getUserId());

    }


    @Test
    public void saveRestaurantAddress(){
        CreateRestaurantAddressRequest restaurantAddressRequest=new CreateRestaurantAddressRequest();
        restaurantAddressRequest.setRestaurantId(1L);
        restaurantAddressRequest.setId(1L);

        RestaurantEntity mockedRestaurant = getRestaurantEntity(restaurantAddressRequest);
        AddressEntity mockedAddress = getAddressEntity(restaurantAddressRequest);
        mockedAddress.setRestaurantEntity(mockedRestaurant);

        when(restaurantFoundationService.getById(any(Long.class))).thenReturn(mockedRestaurant);
        when(addressRepository.saveAddress(any(AddressEntity.class))).thenReturn(mockedAddress);
        when(addressRequestConverter.convert(restaurantAddressRequest)).thenReturn(mockedAddress);

        AddressEntity addressEntity = addressFoundationService.saveRestaurantAddress(restaurantAddressRequest);

        assertNotNull(addressEntity);

        verify(restaurantFoundationService).getById(restaurantAddressRequest.getRestaurantId());
        verify(addressRepository).saveAddress(mockedAddress);
        verify(addressRequestConverter).convert(restaurantAddressRequest);
    }


    private RestaurantEntity getRestaurantEntity(CreateRestaurantAddressRequest restaurantAddressRequest) {
        RestaurantEntity restaurantEntity=new RestaurantEntity();
        restaurantEntity.setId(restaurantAddressRequest.getRestaurantId());
        return restaurantEntity;
    }

    private UserEntity getUserEntity(CreateUserAddressRequest userAddressRequest) {
        UserEntity userEntity=new UserEntity();
        userEntity.setId(userAddressRequest.getUserId());
        return userEntity;
    }

    private <T extends BaseRequest> AddressEntity getAddressEntity(T t) {
        AddressEntity addressEntity=new AddressEntity();
        addressEntity.setId(t.getId());
        return addressEntity;
    }

}
