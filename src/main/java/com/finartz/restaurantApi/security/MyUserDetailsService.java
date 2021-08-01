package com.finartz.restaurantApi.security;


import com.finartz.restaurantApi.dao.UserRepository;
import com.finartz.restaurantApi.error.UserError;
import com.finartz.restaurantApi.exception.ResourceNotFoundException;
import com.finartz.restaurantApi.model.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<UserEntity> user = userRepository.findByUserName(userName);
        // Optional<UserEntity> user = Optional.ofNullable(userRepository.findByUserName(userName).orElseThrow(() -> new ResourceNotFoundException(UserError.USER_NOT_FOUND)));

        if(user.isPresent()){
            return new MyUserDetails(user.get());
        }
        else{
            throw new ResourceNotFoundException(UserError.USER_NOT_FOUND);
        }

    }
}
