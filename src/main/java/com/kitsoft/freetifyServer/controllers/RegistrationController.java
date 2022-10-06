package com.kitsoft.freetifyServer.controllers;

import com.kitsoft.freetifyServer.DTO.UserDTO;
import com.kitsoft.freetifyServer.services.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/registration")
@CrossOrigin("*")
public class RegistrationController {


    @Autowired
    private UserRegistrationService userRegistrationService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String registerUser(@RequestBody UserDTO user) {
        userRegistrationService.createUser(user.getUsername(),user.getPassword());
        return "User registered";
    }


    @PostMapping(path = "/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Map<String,Object> getToken(@RequestParam("username") String username,
                                       @RequestParam("password") String password) {
        String token = userRegistrationService.getToken(username, password);
        Map<String, Object> res = new HashMap<>();
        res.put("token", token);
        return res;
    }
}
