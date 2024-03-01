package com.mak.ecommerce.userservice.repository;

import com.mak.ecommerce.userservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, UUID> {
    public Optional<Token> findByToken(String token);
}
