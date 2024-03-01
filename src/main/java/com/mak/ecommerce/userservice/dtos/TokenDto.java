package com.mak.ecommerce.userservice.dtos;

import com.mak.ecommerce.userservice.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TokenDto {
    private String value;
    private Date expiryAt;
}
