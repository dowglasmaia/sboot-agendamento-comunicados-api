package com.dowglasmaia.agendamento.service;

import com.dowglasmaia.agendamento.models.User;
import org.springframework.http.ResponseEntity;

public interface LoginService<T> {

    ResponseEntity<T> login(User user);


    ResponseEntity<T> refreshToken(String string);
}
