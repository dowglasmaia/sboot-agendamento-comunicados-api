package com.dowglasmaia.agendamento.service;

import com.dowglasmaia.agendamento.models.User;
import org.springframework.http.ResponseEntity;

public interface LoginService {

    ResponseEntity<String>login(User user);
}
