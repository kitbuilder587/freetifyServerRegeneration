package com.kitsoft.freetifyServer.controllers;

import com.kitsoft.freetifyServer.config.KeycloakRoleConverter;
import com.kitsoft.freetifyServer.services.CompositionService;
import com.kitsoft.freetifyServer.services.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/compositions/")
@CrossOrigin("*")
public class CompositionController {

    private Logger logger = Logger.getLogger(CompositionController.class.getName());

    @Autowired
    private CompositionService compositionService;


    @GetMapping(path = "/create")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String addComposition(@AuthenticationPrincipal Jwt principal) {
        return "Hello, " + principal.getClaims().get("user_name");
    }



}
