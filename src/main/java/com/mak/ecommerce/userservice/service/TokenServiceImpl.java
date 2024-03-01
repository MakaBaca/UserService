package com.mak.ecommerce.userservice.service;

import com.mak.ecommerce.userservice.dtos.LoginRequestDto;
import com.mak.ecommerce.userservice.dtos.LogoutRequestDto;
import com.mak.ecommerce.userservice.dtos.TokenDto;
import com.mak.ecommerce.userservice.exception.TokenNotFoundException;
import com.mak.ecommerce.userservice.exception.UserNotFoundException;
import com.mak.ecommerce.userservice.models.Token;
import com.mak.ecommerce.userservice.models.User;
import com.mak.ecommerce.userservice.repository.TokenRepository;
import com.mak.ecommerce.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenServiceInterface{

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    public TokenServiceImpl(UserRepository repo, TokenRepository tRepo){
        this.userRepository = repo;
        this.tokenRepository = tRepo;
    }
    @Override
    public TokenDto getToken(LoginRequestDto dto) throws UserNotFoundException {
        Optional<User> user = userRepository.findByEmail(dto.getEmail());
        if(user.isEmpty()){
            throw new UserNotFoundException("User with email :"+dto.getEmail()+" not found");
        }
        Token token = new Token();
        token.setUser(user.get());
        Instant instant = new Date().toInstant();
        instant.plus(2, ChronoUnit.HOURS);
        token.setExpiryAt(Date.from(instant));
        token.setToken(UUID.randomUUID().toString());
        token = tokenRepository.save(token);
        return transformTokenToDto(token);
    }

    @Override
    public void logout(LogoutRequestDto dto) throws TokenNotFoundException {
        Optional<Token> token = tokenRepository.findByToken(dto.getToken());
        if(token.isEmpty()){
            throw new TokenNotFoundException("Token not found");
        }
        tokenRepository.delete(token.get());
    }

    private TokenDto transformTokenToDto(Token token){
        TokenDto dto = new TokenDto();
        dto.setValue(token.getToken());
        dto.setExpiryAt(token.getExpiryAt());
        return dto;
    }
}
