package com.fernando.to_do_list_api.services.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.fernando.to_do_list_api.dtos.LoginDTO;
import com.fernando.to_do_list_api.dtos.RegisterDTO;
import com.fernando.to_do_list_api.models.AuthUser;

public interface AuthUserService extends UserDetailsService {
    String login(LoginDTO loginDTO);
    void register(RegisterDTO registerDTO);
    AuthUser findByUsername(String username);   
}