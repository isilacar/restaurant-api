package com.finartz.restaurantApi.jUnitTest;

import com.finartz.restaurantApi.dao.UserRepository;
import com.finartz.restaurantApi.model.converter.request.impl.UserRequestConverter;
import com.finartz.restaurantApi.model.entity.UserEntity;
import com.finartz.restaurantApi.model.request.CreateUserRequest;
import com.finartz.restaurantApi.service.foundation.impl.UserFoundationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserFoundationServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserRequestConverter userRequestConverter;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @InjectMocks
    private UserFoundationServiceImpl userFoundationService;

    @Test
    public void getByIdTest(){
        CreateUserRequest userRequest=new CreateUserRequest();
        userRequest.setId(1L);

        UserEntity mockedUser = getUserEntity(userRequest);

        when(userRepository.findById(any(Long.class))).thenReturn(mockedUser);

        UserEntity userEntity = userFoundationService.getById(userRequest.getId());
        assertEquals(userRequest.getId(),userEntity.getId());
        assertNotNull(userEntity);

        verify(userRepository).findById(userRequest.getId());


    }

    @Test
    public void getByUsernameTest(){
        CreateUserRequest userRequest=new CreateUserRequest();
        userRequest.setUserName("test");

        UserEntity mockedEntity = getUserEntity(userRequest);

        when(userRepository.findByUserName(any(String.class))).thenReturn(mockedEntity);

        UserEntity userEntity = userFoundationService.getByUsername(userRequest.getUserName());
        assertNotNull(userEntity);
        assertEquals(userRequest.getUserName(),userEntity.getUserName());

        verify(userRepository).findByUserName(userRequest.getUserName());


    }

    @Test
    public void saveUserTest(){
        CreateUserRequest userRequest=new CreateUserRequest();
        userRequest.setUserName("test");
        UserEntity mockedEntity = getUserEntity(userRequest);

        when(userRepository.saveUser(any(UserEntity.class))).thenReturn(mockedEntity);
        when(userRequestConverter.convertToEntity(any(CreateUserRequest.class))).thenReturn(mockedEntity);

        UserEntity userEntity = userFoundationService.saveUser(userRequest);
        assertNotNull(userEntity);
        assertEquals(userRequest.getUserName(),userEntity.getUserName());

        verify(userRequestConverter).convertToEntity(userRequest);
        verify(userRepository).saveUser(mockedEntity);
    }

    private UserEntity getUserEntity(CreateUserRequest userRequest) {
        UserEntity userEntity=new UserEntity();
        userEntity.setId(userRequest.getId());
        userEntity.setUserName(userRequest.getUserName());

        return userEntity;
    }

}
