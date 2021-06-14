package com.finartz.restaurantApi.security;


import com.finartz.restaurantApi.dao.UserRepository;
import com.finartz.restaurantApi.model.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByUserName(userName);

        if(user!=null){
            return new MyUserDetails(user);
        }
        else{
            throw new RuntimeException("Username Not Found..");
           // throw new UsernameNotFoundException("Username Not Found");
        }

    }
}
