package com.finartz.restaurantApi.controller;

import com.finartz.restaurantApi.controller.endpoint.UserServiceEndpoint;
import com.finartz.restaurantApi.model.dto.UserDto;
import com.finartz.restaurantApi.model.request.CreateUserRequest;
import com.finartz.restaurantApi.service.aggregation.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(UserServiceEndpoint.CreateToken.URI)
    public ResponseEntity<?> createToken(@Valid @RequestBody CreateUserRequest userRequest){
        return ResponseEntity.ok(userService.createToken(userRequest));
    }

    @PostMapping(UserServiceEndpoint.SaveUser.URI)
    public ResponseEntity<UserDto> saveUser( @Valid @RequestBody CreateUserRequest userRequest){
        return ResponseEntity.ok().body(userService.saveUser(userRequest));
    }

    @GetMapping(UserServiceEndpoint.GetUser.URI)
    public ResponseEntity<UserDto> getUser(@PathVariable(value = UserServiceEndpoint.GetUser.PathVariable.ID)
                                                       Long id) {
        return ResponseEntity.ok().body(userService.getUser(id));
    }

}
