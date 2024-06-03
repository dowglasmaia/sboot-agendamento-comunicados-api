package com.dowglasmaia.agendamento.config.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${keycloak.auth-server-url}")
    private String keycloakServerUrl;

    private static final String AUTH_PATH = "/api/v1/auth";
    private static final String AGENDAMENTO_PATH = "/api/v1/comunicados/agendamentos/**";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
              .authorizeHttpRequests(auth -> {
                  auth.requestMatchers(AUTH_PATH + "/login").permitAll();
                  auth.requestMatchers(AUTH_PATH + "/refresh").permitAll();

                  auth.requestMatchers(HttpMethod.POST, AGENDAMENTO_PATH)
                        .hasAnyAuthority("ADMIN_READ","ADMIN_WRITE");

                  auth.requestMatchers(HttpMethod.GET, AGENDAMENTO_PATH)
                        .hasAnyAuthority("OPERATION_WRITE","OPERATION_READ");

                  auth.requestMatchers(HttpMethod.DELETE, AGENDAMENTO_PATH)
                        .hasAnyAuthority("ADMIN_READ","ADMIN_WRITE");

              }).oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.decoder(jwtDecoder())));


        return http.build();
    }


    @Bean
    public JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder
              .withJwkSetUri(keycloakServerUrl + "/protocol/openid-connect/certs")
              .build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverterForKeycloak(){
        Converter<Jwt, Collection<GrantedAuthority>> converter = jwt -> {
            Map<String, Object> resourceAccess = jwt.getClaim("realm_access");
            Collection<String> roles = (Collection<String>) resourceAccess.get("roles");
            return roles.stream()
                  .map(SimpleGrantedAuthority::new)
                  .collect(Collectors.toList());
        };

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(converter);

        return jwtAuthenticationConverter;
    }

}
