package com.mak.ecommerce.userservice.service;

import com.mak.ecommerce.userservice.dtos.LoginRequestDto;
import com.mak.ecommerce.userservice.dtos.SignUpRequestDto;
import com.mak.ecommerce.userservice.dtos.TokenDto;
import com.mak.ecommerce.userservice.dtos.UserDto;
import com.mak.ecommerce.userservice.exception.UserNotFoundException;
import com.mak.ecommerce.userservice.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserServiceInterface {
    public UserDto createUser(SignUpRequestDto dto);
    public User getUserById(UUID id) throws UserNotFoundException;
    public List<User> getAllUsers();
    public User updateUser(User user);
    public void updatePassword(UUID id, String password);
}
