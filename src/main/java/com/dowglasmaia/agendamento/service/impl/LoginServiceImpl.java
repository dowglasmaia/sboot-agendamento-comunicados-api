package com.dowglasmaia.agendamento.service.impl;

import com.dowglasmaia.agendamento.config.components.HttpComponent;
import com.dowglasmaia.agendamento.models.User;
import com.dowglasmaia.agendamento.service.LoginService;
import com.dowglasmaia.agendamento.util.HttpParamsMapBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class LoginServiceImpl implements LoginService {


    @Value("${keycloak.auth-server-url}")
    private String keycloakServerUrl;

    @Value("${keycloak.realm}")
    private String realm;



    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    @Value("${keycloak.user-login.grant-type}")
    private String grantType;

    @Autowired
    private HttpComponent httpComponent;


    @Override
    public ResponseEntity<String> login(User user){
        httpComponent.httpHeaders().setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = HttpParamsMapBuilder.builder()
              .withClent(clientId)
              .withClientSecret(clientSecret)
              .withgrantType(grantType)
              .withPassword(user.getPassword())
              .withUsername(user.getUserName())
              .build();

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, httpComponent.httpHeaders());

        try {
            ResponseEntity<String> response = httpComponent.restTemplate().postForEntity(
                  keycloakServerUrl + "/protocol/openid-connect/token",
                  request,
                  String.class
            );
            return ResponseEntity.ok(response.getBody());

        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> refreshToken(String refreshToken){
        httpComponent.httpHeaders().setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = HttpParamsMapBuilder.builder()
              .withClent(clientId)
              .withClientSecret(clientSecret)
              .withgrantType("refresh_token")
              .withRefreshToken(refreshToken)
              .build();

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, httpComponent.httpHeaders());

        try {
            ResponseEntity<String> response = httpComponent.restTemplate().postForEntity(
                  keycloakServerUrl + "/protocol/openid-connect/token",
                  request,
                  String.class
            );
            return ResponseEntity.ok(response.getBody());

        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }

}
