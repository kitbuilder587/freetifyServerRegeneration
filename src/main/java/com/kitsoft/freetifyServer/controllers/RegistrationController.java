package com.kitsoft.freetifyServer.controllers;

import com.kitsoft.freetifyServer.services.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
@CrossOrigin("*")
public class RegistrationController {


    @Autowired
    private UserRegistrationService userRegistrationService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String addComposition(@RequestParam("username") String username,
                                 @RequestParam("password") String password) {
        userRegistrationService.createUser(username,password);
        return "User registered";
    }
}
