package com.mak.ecommerce.userservice.repository;

import com.mak.ecommerce.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Modifying
    @Query("update User u set u.hashedPassword = ?2 where u.id = ?1")
    public void updatePassword(UUID id, String password);
    public Optional<User> findByEmail(String email);
}
