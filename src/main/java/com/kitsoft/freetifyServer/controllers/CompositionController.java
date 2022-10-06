package com.kitsoft.freetifyServer.controllers;

import com.kitsoft.freetifyServer.components.StorageComponent;
import com.kitsoft.freetifyServer.config.KeycloakRoleConverter;
import com.kitsoft.freetifyServer.entities.Composition;
import com.kitsoft.freetifyServer.services.CompositionService;
import com.kitsoft.freetifyServer.services.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private StorageComponent storageComponent;

    @GetMapping
    // as an example @PreAuthorize("hasRole('ROLE_user')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Composition> getCompositions(@AuthenticationPrincipal Jwt principal) {
        return compositionService.getCompositions();
    }

    @GetMapping("/storageResource/{id}")
    // as an example @PreAuthorize("hasRole('ROLE_user')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity getStorageResource(@PathVariable("id") Long id, @AuthenticationPrincipal Jwt principal) {
        ResponseEntity entity = ResponseEntity.ok()
                .contentLength((int)storageComponent.getFile(id).length())
                .contentType(MediaType.parseMediaType("multipart/form-data"))
                .body(new FileSystemResource(storageComponent.getFile(id)));
        return entity;
    }

    @GetMapping(path = "/create")
   // as an example @PreAuthorize("hasRole('ROLE_user')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String addComposition(@AuthenticationPrincipal Jwt principal) {
        return "Hello, " + principal.getClaims().get("user_name");
    }



}
