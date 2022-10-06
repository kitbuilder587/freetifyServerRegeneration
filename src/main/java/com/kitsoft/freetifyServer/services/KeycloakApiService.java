package com.kitsoft.freetifyServer.services;

import aj.org.objectweb.asm.TypeReference;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class KeycloakApiService {

    @Value("${keycloak.authsecret}")
    String clientSecret;

    @Value("${keycloak.authusername}")
    String clientUsername;


    @Value("${keycloak.authpassword}")
    String clientPassword;

    @Value("${spring.security.oauth2.client.provider.keycloak.token-uri}")
    String tokenResource;

    @Value("${keycloak.realm}")
    String keycloakRealm;

    @Value("${keycloak.host}")
    String keycloakHost;


    RestTemplate restTemplate = new RestTemplate();

    public String getAccessToken(String username, String password){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/x-www-form-urlencoded"));


        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("grant_type", "password");
        map.add("username", username);
        map.add("password", password);
        map.add("client_id", "freetify-oauth2-client");
        map.add("client_secret", clientSecret);



        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity( tokenResource, request, Map.class );

        Map<String,String> s = response.getBody();

        return (String) s.get("access_token");
    }

    public String getAdminAccessToken(){
        return getAccessToken(clientUsername,clientPassword);
    }

    public List<String> getUsersNames(String accessToken){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/x-www-form-urlencoded"));
        headers.add("Authorization", "Bearer " + accessToken);
        HttpEntity<Object> request = new HttpEntity<Object>(headers);
        String apiUrl = keycloakHost + "auth/admin/realms/" + keycloakRealm + "/users";
        ResponseEntity<Object> response = restTemplate.exchange(apiUrl, HttpMethod.GET, request, new ParameterizedTypeReference<Object>() {});
        List<Map<String,Object>> body = (List<Map<String,Object>>) response.getBody();
        return body.stream().map(m -> (String)m.get("username")).collect(Collectors.toList());
    }

    public void addUser(String accessToken, String username, String password){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + accessToken);
        JSONObject credentials = new JSONObject();
        credentials.put("type","password");
        credentials.put("value", password);
        credentials.put("temporary", false);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("credentials", credentials);
        HttpEntity<String> request = new HttpEntity<String>(jsonObject.toJSONString(),headers);
        String apiUrl = keycloakHost + "auth/admin/realms/" + keycloakRealm + "/users";
        ResponseEntity<Object> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, new ParameterizedTypeReference<Object>() {});
    }
}
