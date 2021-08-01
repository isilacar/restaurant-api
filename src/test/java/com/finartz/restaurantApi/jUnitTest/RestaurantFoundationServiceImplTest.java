package com.finartz.restaurantApi.jUnitTest;


import com.finartz.restaurantApi.dao.RestaurantRepository;
import com.finartz.restaurantApi.model.converter.dto.impl.RestaurantDTOConverter;
import com.finartz.restaurantApi.model.converter.dto.impl.UserDTOConverter;
import com.finartz.restaurantApi.model.dto.RestaurantDto;
import com.finartz.restaurantApi.model.dto.UserDto;
import com.finartz.restaurantApi.model.entity.RestaurantEntity;
import com.finartz.restaurantApi.model.entity.UserEntity;
import com.finartz.restaurantApi.model.enumeration.RestaurantStatus;
import com.finartz.restaurantApi.security.TokenManager;
import com.finartz.restaurantApi.service.foundation.UserFoundationService;
import com.finartz.restaurantApi.service.foundation.impl.RestaurantFoundationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantFoundationServiceImplTest {

    public static final String AUTHORIZATION = "Authorization";

    @Mock
    private RestaurantRepository restaurantRepository;
    @Mock
    private RestaurantDTOConverter restaurantConverter;
    @Mock
    private UserFoundationService userFoundationService;
    @Mock
    private TokenManager tokenManager;
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private UserDTOConverter userDTOConverter;
    @InjectMocks
    private RestaurantFoundationServiceImpl restaurantFoundationService;

    @Test
    public void saveRestaurantTest() {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(1L);

        RestaurantEntity mockedRestaurant = getRestaurantEntity(restaurantDto);

        when(restaurantConverter.convertToEntity(any(RestaurantDto.class))).thenReturn(mockedRestaurant);
        when(restaurantRepository.saveRestaurant(any(RestaurantEntity.class))).thenReturn(mockedRestaurant);
        when(restaurantConverter.convertToDto(any(RestaurantEntity.class))).thenReturn(restaurantDto);

        RestaurantDto returnedDto = restaurantFoundationService.saveRestaurant(restaurantDto);
        assertNotNull(returnedDto.getId());
        assertEquals(restaurantDto.getId(), returnedDto.getId());

        verify(restaurantConverter).convertToEntity(restaurantDto);
        verify(restaurantRepository).saveRestaurant(mockedRestaurant);
        verify(restaurantConverter).convertToDto(mockedRestaurant);
    }

    @Test
    public void getByIdTest() {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(1L);

        RestaurantEntity mockedRestaurant = getRestaurantEntity(restaurantDto);

        when(restaurantRepository.findRestaurant(any(Long.class))).thenReturn(Optional.of(mockedRestaurant));
        when(restaurantConverter.convertToDto(any(RestaurantEntity.class))).thenReturn(restaurantDto);

        RestaurantDto returnedDto = restaurantFoundationService.getRestaurant(restaurantDto.getId());
        assertNotNull(returnedDto);
        assertTrue(returnedDto.getId() == restaurantDto.getId());

        verify(restaurantRepository).findRestaurant(restaurantDto.getId());
        verify(restaurantConverter).convertToDto(mockedRestaurant);
    }

    @Test
    public void updateRestaurantTest() {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(1L);
        restaurantDto.setName("test");

        RestaurantEntity mockedRestaurant = getRestaurantEntityUpdate(restaurantDto, "testUpdated");

        doNothing().when(restaurantRepository).updateRestaurant(any(Long.class), any(RestaurantEntity.class));
        when(restaurantRepository.findRestaurant(any(Long.class))).thenReturn(Optional.of(mockedRestaurant));


        restaurantRepository.updateRestaurant(restaurantDto.getId(), mockedRestaurant);
        restaurantFoundationService.updateRestaurant(restaurantDto.getId(), restaurantDto);

        verify(restaurantRepository).findRestaurant(restaurantDto.getId());
        verify(restaurantRepository, times(2)).updateRestaurant(restaurantDto.getId(), mockedRestaurant);
    }

    @Test
    public void getAllRestaurantByStatusTest() {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setStatus(RestaurantStatus.APPROVED);
        RestaurantEntity mockedRestaurant = getRestaurantEntity(restaurantDto);

        when(restaurantRepository.findRestaurantByStatus(any(RestaurantStatus.class))).thenReturn(Arrays.asList(mockedRestaurant));
        when(restaurantConverter.convertToDtoList(anyList())).thenReturn(Arrays.asList(restaurantDto));

        List<RestaurantDto> allRestaurantByStatus = restaurantFoundationService.getAllRestaurantByStatus(restaurantDto.getStatus());
        assertNotNull(allRestaurantByStatus);

        verify(restaurantRepository).findRestaurantByStatus(restaurantDto.getStatus());
        verify(restaurantConverter).convertToDtoList(Arrays.asList(mockedRestaurant));


    }

    @Test
    public void getAllRestaurantsTest() {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setStatus(RestaurantStatus.APPROVED);
        RestaurantDto restaurantDto1 = new RestaurantDto();
        restaurantDto1.setStatus(RestaurantStatus.REJECTED);

        List<RestaurantDto> restaurantDtoList = new ArrayList<>();
        restaurantDtoList.add(restaurantDto);
        restaurantDtoList.add(restaurantDto1);

        List<RestaurantEntity> mockedList = getRestaurantEntityList(restaurantDtoList);
        when(restaurantRepository.getAll()).thenReturn(mockedList);
        when(restaurantConverter.convertToDtoList(anyList())).thenReturn(restaurantDtoList);

        List<RestaurantDto> returnedRestaurants = restaurantFoundationService.getAllRestaurants();
        assertNotNull(returnedRestaurants);
        assertTrue(returnedRestaurants.size() > 0);
        verify(restaurantRepository).getAll();
        verify(restaurantConverter).convertToDtoList(mockedList);
    }


    @Test
    public void getAllNearestRestaurantByApproved() {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setName("testRest1");
        restaurantDto.setId(1L);
        restaurantDto.setLatitude(40.999829);
        restaurantDto.setLongitude(29.059281);
        restaurantDto.setStatus(RestaurantStatus.APPROVED);

        RestaurantDto restaurantDto1 = new RestaurantDto();
        restaurantDto1.setName("testRest2");
        restaurantDto1.setId(2L);
        restaurantDto1.setLatitude(40.999830);
        restaurantDto1.setLongitude(29.059200);
        restaurantDto1.setStatus(RestaurantStatus.APPROVED);

        RestaurantDto restaurantDto2 = new RestaurantDto();
        restaurantDto2.setName("testRest3");
        restaurantDto2.setId(3L);
        restaurantDto2.setLatitude(40.999832);
        restaurantDto2.setLongitude(29.059280);
        restaurantDto2.setStatus(RestaurantStatus.APPROVED);

        List<RestaurantDto> restaurantDtoList = new ArrayList<>();
        restaurantDtoList.add(restaurantDto);
        restaurantDtoList.add(restaurantDto1);
        restaurantDtoList.add(restaurantDto2);

        UserDto userDto = new UserDto();
        userDto.setUserName("test");
        userDto.setLatitude(40.976827);
        userDto.setLongitude(29.103555);

        UserEntity mockedUser = getUserEntity(userDto);

        List<RestaurantEntity> mockedRestaurantList = getRestaurantEntityList(restaurantDtoList);

        String bearer = "Bearer token-test";
        String token="token-test";
        String username = "test";

        when(httpServletRequest.getHeader(any(String.class))).thenReturn(bearer);
        when(tokenManager.getUsernameFromToken(any(String.class))).thenReturn(username);
        when(userFoundationService.getByUsername(any(String.class))).thenReturn(userDto);
        when(userDTOConverter.convertToEntity(any(UserDto.class))).thenReturn(mockedUser);
        when(restaurantRepository.findNearestRestaurantByStatusApproved(any(Long.class))).thenReturn(mockedRestaurantList);
        when(restaurantConverter.convertToDtoList(anyList())).thenReturn(restaurantDtoList);

        List<RestaurantDto> returnedRestaurantList = restaurantFoundationService.getAllNearestRestaurantByApproved();
        assertNotNull(returnedRestaurantList);
        assertEquals(returnedRestaurantList.size(),3);

        verify(httpServletRequest).getHeader(AUTHORIZATION);
        verify(tokenManager).getUsernameFromToken(token);
        verify(userFoundationService).getByUsername(username);
        verify(userDTOConverter).convertToEntity(userDto);
        verify(restaurantRepository).findNearestRestaurantByStatusApproved(userDto.getId());
       // verify(restaurantConverter).convertToDtoList(mockedRestaurantList);
    }

    private UserEntity getUserEntity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDto.getId());
        userEntity.setUserName(userDto.getUserName());
        userEntity.setLatitude(userDto.getLatitude());
        userEntity.setLongitude(userDto.getLongitude());
        return userEntity;
    }

    private RestaurantEntity getRestaurantEntityUpdate(RestaurantDto restaurantDto, String testUpdated) {
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantDto.setName(testUpdated);
        restaurantEntity.setId(restaurantDto.getId());
        restaurantEntity.setName(restaurantDto.getName());
        return restaurantEntity;
    }

    private RestaurantEntity getRestaurantEntity(RestaurantDto restaurantDto) {
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setId(restaurantDto.getId());
        restaurantEntity.setStatus(restaurantDto.getStatus());
        if (restaurantDto.getName() != null) {
            restaurantEntity.setName(restaurantDto.getName());
        }
        if (restaurantDto.getLongitude() != null) {
            restaurantEntity.setLongitude(restaurantDto.getLongitude());
        }
        if (restaurantDto.getLatitude() != null) {
            restaurantEntity.setLatitude(restaurantDto.getLatitude());
        }
        return restaurantEntity;
    }

    private List<RestaurantEntity> getRestaurantEntityList(List<RestaurantDto> restaurantDtoList) {
        List<RestaurantEntity> restaurantEntityList = new ArrayList<>();
        for (RestaurantDto dto : restaurantDtoList) {
            restaurantEntityList.add(getRestaurantEntity(dto));
        }
        return restaurantEntityList;
    }

}
