package com.fernando.to_do_list_api.services.interfaces;

import com.fernando.to_do_list_api.models.AuthUser;

public interface TokenJWTService {
    String generateToken(AuthUser user);
    String getSubject(String token);
}