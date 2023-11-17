package com.fernando.to_do_list_api.services.impl;

import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fernando.to_do_list_api.models.AuthUser;
import com.fernando.to_do_list_api.services.interfaces.TokenJWTService;

@Service
public class TokenJWTServiceJavaJWT implements TokenJWTService {

    @Override
    public String generateToken(AuthUser user) {
        try {
           return JWT
           .create()
           .withSubject(user.getUsername()) 
           .withIssuer("to-do-list-api")
           .sign(Algorithm.HMAC256("123456"));
        }
        catch(Exception e) {
            return null;
        }
    }

    @Override
    public String getSubject(String token) {
       try {
           return JWT
           .require(Algorithm.HMAC256("123456"))
           .build()
           .verify(token)
           .getSubject();
        }
        catch(Exception e) {
            return null;
        }
    }
    
}