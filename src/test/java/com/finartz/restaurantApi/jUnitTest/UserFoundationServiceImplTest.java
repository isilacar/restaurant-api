package com.finartz.restaurantApi.jUnitTest;

import com.finartz.restaurantApi.dao.UserRepository;
import com.finartz.restaurantApi.model.converter.dto.impl.UserDTOConverter;
import com.finartz.restaurantApi.model.dto.UserDto;
import com.finartz.restaurantApi.model.entity.UserEntity;
import com.finartz.restaurantApi.service.foundation.impl.UserFoundationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserFoundationServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    private  UserDTOConverter userDTOConverter;
    @InjectMocks
    private UserFoundationServiceImpl userFoundationService;

    @Test
    public void getByIdTest(){
        UserDto userDto=new UserDto();
        userDto.setId(1L);

        UserEntity mockedUser = getUserEntity(userDto);

        when(userRepository.findUser(any(Long.class))).thenReturn(Optional.of(mockedUser));
        when(userDTOConverter.convertToDto(any(UserEntity.class))).thenReturn(userDto);

        UserDto returnedDto = userFoundationService.getUser(userDto.getId());
        assertEquals(userDto.getId(),returnedDto.getId());
        assertNotNull(returnedDto);

        verify(userRepository).findUser(userDto.getId());
        verify(userDTOConverter).convertToDto(mockedUser);


    }

    @Test
    public void getByUsernameTest(){
       UserDto userDto=new UserDto();
        userDto.setUserName("test");

        UserEntity mockedEntity = getUserEntity(userDto);

        when(userRepository.findByUserName(any(String.class))).thenReturn(Optional.of(mockedEntity));
        when(userDTOConverter.convertToDto(any(UserEntity.class))).thenReturn(userDto);

        UserDto returnedDto = userFoundationService.getByUsername(userDto.getUserName());
        assertNotNull(returnedDto);
        assertEquals(userDto.getUserName(),returnedDto.getUserName());

        verify(userRepository).findByUserName(userDto.getUserName());
        verify(userDTOConverter).convertToDto(mockedEntity);


    }

    @Test
    public void saveUserTest(){
        UserDto userDto=new UserDto();
        userDto.setUserName("test");

        UserEntity mockedEntity = getUserEntity(userDto);

        when(userRepository.saveUser(any(UserEntity.class))).thenReturn(mockedEntity);
        when(userDTOConverter.convertToDto(any(UserEntity.class))).thenReturn(userDto);
        when(userDTOConverter.convertToEntity(any(UserDto.class))).thenReturn(mockedEntity);

        UserDto returnedDto = userFoundationService.saveUser(userDto);
        assertNotNull(returnedDto);
        assertEquals(userDto.getUserName(),returnedDto.getUserName());

        verify(userDTOConverter).convertToDto(mockedEntity);
        verify(userRepository).saveUser(mockedEntity);
        verify(userDTOConverter).convertToEntity(userDto);
    }



    private UserEntity getUserEntity(UserDto userDto) {
        UserEntity userEntity=new UserEntity();
        userEntity.setId(userDto.getId());
        userEntity.setUserName(userDto.getUserName());

        return userEntity;
    }



}
