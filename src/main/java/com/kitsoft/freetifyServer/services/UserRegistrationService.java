package com.kitsoft.freetifyServer.services;

import com.kitsoft.freetifyServer.entities.User;
import com.kitsoft.freetifyServer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.UnAuthenticatedServerOAuth2AuthorizedClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.persistence.OneToMany;
import java.util.*;
import java.util.logging.Logger;

@Service
public class UserRegistrationService {

    @Autowired
    KeycloakApiService keycloakApiService;

    @Autowired
    UserRepository userRepository;

    public void createUser(String username, String password) {
        String token = keycloakApiService.getAdminAccessToken();
        keycloakApiService.addUser(token, username,password);
        User user = new User();
        user.setUsername(username);
        userRepository.save(user);
    }

    public String getToken(String username, String password){
        return keycloakApiService.getAccessToken(username, password);
    }


}
