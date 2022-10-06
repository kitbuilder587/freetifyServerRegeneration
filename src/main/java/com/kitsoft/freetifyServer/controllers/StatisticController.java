package com.kitsoft.freetifyServer.controllers;


import com.kitsoft.freetifyServer.DTO.CompositionDTO;
import com.kitsoft.freetifyServer.entities.Composition;
import com.kitsoft.freetifyServer.services.CompositionService;
import com.kitsoft.freetifyServer.services.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/statistic")
@CrossOrigin("*")
public class StatisticController {


    @Autowired
    PrincipalService principalService;

    @Autowired
    CompositionService compositionService;

    public static String goodResponse = "{\"response\":Successfully registered listener}";
    @PostMapping(path = "/listener")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity postListener(@AuthenticationPrincipal Jwt principal, @RequestBody CompositionDTO composition) {
        String username  = principalService.getUsername(principal);
        compositionService.addListener(composition.getId(), username);
        return ResponseEntity.ok().body( goodResponse );
    }

    @PostMapping(path = "/listeners")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity postListeners(@AuthenticationPrincipal Jwt principal, @RequestBody List<CompositionDTO> compositions) {
        for(int i=0;i<compositions.size();i++){
            postListener(principal,compositions.get(i));
        }
        return ResponseEntity.ok().body( goodResponse );
    }

}
