package com.kitsoft.freetifyServer.services;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class PrincipalService {

    public String getUsername(Jwt principal){
        return (String) principal.getClaims().get("user_name");
    }
}
