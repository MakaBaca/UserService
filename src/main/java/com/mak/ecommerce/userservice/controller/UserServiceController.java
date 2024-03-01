package com.mak.ecommerce.userservice.controller;

import com.mak.ecommerce.userservice.dtos.*;
import com.mak.ecommerce.userservice.exception.TokenNotFoundException;
import com.mak.ecommerce.userservice.exception.UserNotFoundException;
import com.mak.ecommerce.userservice.models.User;
import com.mak.ecommerce.userservice.service.TokenServiceInterface;
import com.mak.ecommerce.userservice.service.UserServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserServiceController {
    private final UserServiceInterface userService;
    private final TokenServiceInterface tokenService;

    UserServiceController(UserServiceInterface impl, TokenServiceInterface tokenService){
        this.userService = impl;
        this.tokenService = tokenService;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable UUID id) throws UserNotFoundException {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/getLogin")
    public TokenDto login(@RequestBody LoginRequestDto requestDto) throws UserNotFoundException {
        // check if email and password in db
        // if yes create token (use random string) return token
        // else throw some error
        return tokenService.getToken(requestDto);
    }

    @PostMapping
    public UserDto signUp(@RequestBody SignUpRequestDto dto) {
        // no need to hash password for now
        // just store user as is in the db
        // for now no need to have email verification either
        return userService.createUser(dto);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto dto) throws TokenNotFoundException {
        // delete token if exists -> 200
        // if doesn't exist give a 404
        tokenService.logout(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
