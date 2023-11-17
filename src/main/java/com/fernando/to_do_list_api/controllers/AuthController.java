package com.fernando.to_do_list_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fernando.to_do_list_api.dtos.LoginDTO;
import com.fernando.to_do_list_api.dtos.RegisterDTO;
import com.fernando.to_do_list_api.services.interfaces.AuthUserService;
import jakarta.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthUserService authUserService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO registerDTO) {
        authUserService.register(registerDTO);
        return ResponseEntity.created(URI.create("/auth/register")).build();
    } 

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO loginDTO) {
        String token = authUserService.login(loginDTO);
        return ResponseEntity.ok().body(token);
    } 
}
