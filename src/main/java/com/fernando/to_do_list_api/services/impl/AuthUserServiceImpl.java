package com.fernando.to_do_list_api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.fernando.to_do_list_api.dtos.LoginDTO;
import com.fernando.to_do_list_api.dtos.RegisterDTO;
import com.fernando.to_do_list_api.mappers.AuthUserMapper;
import com.fernando.to_do_list_api.models.AuthUser;
import com.fernando.to_do_list_api.repositories.AuthUserRepository;
import com.fernando.to_do_list_api.services.interfaces.AuthUserService;
import com.fernando.to_do_list_api.services.interfaces.TokenJWTService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AuthUserServiceImpl implements AuthUserService {
    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private AuthUserMapper authUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Lazy
    private AuthenticationManager authManager;

    @Autowired
    private TokenJWTService tokenJWTService;

    @Override
    public AuthUser findByUsername(String username) {
        return authUserRepository.findByUsername(username)
        .orElseThrow(
            () -> new EntityNotFoundException("User is not exists :()"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username);
    }

    @Override
    public String login(LoginDTO loginDTO) {
        String username = loginDTO.username();
        String password = loginDTO.password();

        var authToken = new UsernamePasswordAuthenticationToken(username, password);
        var authentication = authManager.authenticate(authToken);
        var authUser = (AuthUser) authentication.getPrincipal();
        var token = tokenJWTService.generateToken(authUser);
        return token;
    }

    @Override
    public void register(RegisterDTO registerDTO) {
        String username = registerDTO.username();
        String encodedPassword = passwordEncoder.encode(registerDTO.password());
        Optional<AuthUser> authUserOptional = authUserRepository.findByUsername(username);

        if (authUserOptional.isPresent()) {
            throw new EntityExistsException("User is already exists");
        }

        RegisterDTO userRegisterDTO = new RegisterDTO(username, encodedPassword);

        authUserRepository.save(authUserMapper.toModel(userRegisterDTO));
        
    }
}