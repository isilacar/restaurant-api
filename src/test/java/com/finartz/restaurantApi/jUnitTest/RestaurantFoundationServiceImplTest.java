package com.finartz.restaurantApi.jUnitTest;


import com.finartz.restaurantApi.dao.RestaurantRepository;
import com.finartz.restaurantApi.model.converter.request.impl.RestaurantRequestConverter;
import com.finartz.restaurantApi.model.entity.RestaurantEntity;
import com.finartz.restaurantApi.model.entity.UserEntity;
import com.finartz.restaurantApi.model.enumeration.StatusEnum;
import com.finartz.restaurantApi.model.request.CreateRestaurantRequest;
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

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantFoundationServiceImplTest {

    @Mock
    private RestaurantRepository restaurantRepository;
    @Mock
    private RestaurantRequestConverter restaurantRequestConverter;
    @Mock
    private UserFoundationService userFoundationService;
    @Mock
    private TokenManager tokenManager;
    @Mock
    private HttpServletRequest httpServletRequest;
    @InjectMocks
    private RestaurantFoundationServiceImpl restaurantFoundationService;

    @Test
    public void saveRestaurantTest() {
        CreateRestaurantRequest createRestaurantRequest = new CreateRestaurantRequest();
        createRestaurantRequest.setId(1L);

        RestaurantEntity mockedRestaurant = getRestaurantEntity(createRestaurantRequest);

        when(restaurantRequestConverter.convertToEntity(any(CreateRestaurantRequest.class))).thenReturn(mockedRestaurant);
        when(restaurantRepository.saveRestaurant(any(RestaurantEntity.class))).thenReturn(mockedRestaurant);

        RestaurantEntity restaurantEntity = restaurantFoundationService.saveRestaurant(createRestaurantRequest);
        assertNotNull(restaurantEntity);
        assertEquals(createRestaurantRequest.getId(), restaurantEntity.getId());

        verify(restaurantRequestConverter).convertToEntity(createRestaurantRequest);
        verify(restaurantRepository).saveRestaurant(mockedRestaurant);
    }

    @Test
    public void getByIdTest() {
        CreateRestaurantRequest createRestaurantRequest = new CreateRestaurantRequest();
        createRestaurantRequest.setId(1L);

        RestaurantEntity mockedRestaurant = getRestaurantEntity(createRestaurantRequest);

        when(restaurantRepository.findById(any(Long.class))).thenReturn(mockedRestaurant);

        RestaurantEntity restaurantEntity = restaurantFoundationService.getById(createRestaurantRequest.getId());
        assertNotNull(restaurantEntity);
        assertTrue(createRestaurantRequest.getId() == restaurantEntity.getId());

        verify(restaurantRepository).findById(createRestaurantRequest.getId());
    }

    @Test
    public void getAllRestaurantByStatusTest() {
        CreateRestaurantRequest createRestaurantRequest = new CreateRestaurantRequest();
        createRestaurantRequest.setStatus(StatusEnum.APPROVED);
        RestaurantEntity mockedRestaurant = getRestaurantEntity(createRestaurantRequest);

        when(restaurantRepository.findRestaurantByStatus(any(StatusEnum.class))).thenReturn(Arrays.asList(mockedRestaurant));

        List<RestaurantEntity> allRestaurantByStatus = restaurantFoundationService.getAllRestaurantByStatus(createRestaurantRequest.getStatus());
        assertNotNull(allRestaurantByStatus);

        verify(restaurantRepository).findRestaurantByStatus(createRestaurantRequest.getStatus());


    }

    @Test
    public void getAllNearestRestaurantByApproved() {
        RestaurantEntity createRestaurantRequest = new RestaurantEntity();
        createRestaurantRequest.setName("testRest1");
        createRestaurantRequest.setId(1L);
        createRestaurantRequest.setLatitude(40.999829);
        createRestaurantRequest.setLongitude(29.059281);
        createRestaurantRequest.setStatus(StatusEnum.APPROVED);

        RestaurantEntity createRestaurantRequest2 = new RestaurantEntity();
        createRestaurantRequest2.setName("testRest2");
        createRestaurantRequest2.setId(2L);
        createRestaurantRequest2.setLatitude(40.999830);
        createRestaurantRequest2.setLongitude(29.059200);
        createRestaurantRequest2.setStatus(StatusEnum.APPROVED);

        RestaurantEntity createRestaurantRequest3 = new RestaurantEntity();
        createRestaurantRequest3.setName("testRest3");
        createRestaurantRequest3.setId(3L);
        createRestaurantRequest3.setLatitude(40.999832);
        createRestaurantRequest3.setLongitude(29.059280);
        createRestaurantRequest3.setStatus(StatusEnum.APPROVED);

        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("test");
        userEntity.setLatitude(40.976827);
        userEntity.setLongitude(29.103555);

        List<RestaurantEntity> mockedList = new ArrayList<>();
        mockedList.add(createRestaurantRequest);
        mockedList.add(createRestaurantRequest2);
        mockedList.add(createRestaurantRequest3);

        String token ="test-token";
        String username = tokenManager.getUsernameFromToken(token);

   //     when(tokenManager.getUsernameFromToken(any(String.class))).thenReturn(username);
        when(userFoundationService.getByUsername(username)).thenReturn(userEntity);
        when(restaurantRepository.findNearestRestaurantByStatusApproved(userEntity.getId())).thenReturn(mockedList);

        List<RestaurantEntity> allNearestRestaurantByApproved = restaurantFoundationService.getAllNearestRestaurantByApproved();
        assertNotNull(allNearestRestaurantByApproved);
        assertEquals(allNearestRestaurantByApproved.size(),mockedList.size());

        verify(userFoundationService).getByUsername(username);
        verify(restaurantRepository).findNearestRestaurantByStatusApproved(userEntity.getId());
      //  verify(tokenManager).getUsernameFromToken(token);

    }

    @Test
    public void updateRestaurantTest() {
        CreateRestaurantRequest createRestaurantRequest = new CreateRestaurantRequest();
        createRestaurantRequest.setId(1L);
        createRestaurantRequest.setName("test");

        RestaurantEntity mockedRestaurant = getRestaurantEntityUpdate(createRestaurantRequest, "testUpdated");

        doNothing().when(restaurantRepository).updateRestaurant(any(Long.class), any(RestaurantEntity.class));
        when(restaurantRepository.findById(any(Long.class))).thenReturn(mockedRestaurant);


        restaurantRepository.updateRestaurant(createRestaurantRequest.getId(), mockedRestaurant);
        restaurantFoundationService.updateRestaurant(createRestaurantRequest.getId(), createRestaurantRequest);


        verify(restaurantRepository).findById(createRestaurantRequest.getId());
        verify(restaurantRepository, times(2)).updateRestaurant(createRestaurantRequest.getId(), mockedRestaurant);
    }

    private RestaurantEntity getRestaurantEntityUpdate(CreateRestaurantRequest createRestaurantRequest, String testUpdated) {
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        createRestaurantRequest.setName(testUpdated);
        restaurantEntity.setId(createRestaurantRequest.getId());
        restaurantEntity.setName(createRestaurantRequest.getName());
        return restaurantEntity;
    }

    private RestaurantEntity getRestaurantEntity(CreateRestaurantRequest createRestaurantRequest) {
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setId(createRestaurantRequest.getId());
        restaurantEntity.setStatus(createRestaurantRequest.getStatus());
        return restaurantEntity;
    }

}
