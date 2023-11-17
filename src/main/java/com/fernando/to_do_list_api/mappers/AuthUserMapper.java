package com.fernando.to_do_list_api.mappers;

import org.springframework.stereotype.Component;

import com.fernando.to_do_list_api.dtos.RegisterDTO;
import com.fernando.to_do_list_api.models.AuthUser;

@Component
public class AuthUserMapper {
    public AuthUser toModel(RegisterDTO registerDTO) {
        AuthUser authUser = new AuthUser();
        authUser.setUsername(registerDTO.username());
        authUser.setPassword(registerDTO.password());

        return authUser;
    }
}