package com.mak.ecommerce.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDto {
    UUID id;
    String name;
    String email;
}
