package com.mak.ecommerce.userservice.service;

import com.mak.ecommerce.userservice.dtos.LoginRequestDto;
import com.mak.ecommerce.userservice.dtos.LogoutRequestDto;
import com.mak.ecommerce.userservice.dtos.TokenDto;
import com.mak.ecommerce.userservice.exception.TokenNotFoundException;
import com.mak.ecommerce.userservice.exception.UserNotFoundException;

public interface TokenServiceInterface {
    public TokenDto getToken(LoginRequestDto dto) throws UserNotFoundException;
    public void logout(LogoutRequestDto dto) throws TokenNotFoundException;
}
