package com.dowglasmaia.agendamento.controller;


import com.dowglasmaia.agendamento.service.AgendamentoService;
import com.dowglasmaia.provider.api.ComunicadosApi;
import com.dowglasmaia.provider.model.AgendamentoComunicacaoRequestDTO;
import com.dowglasmaia.provider.model.AgendamentoComunicacaoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AgendamentoController implements ComunicadosApi {

    private final AgendamentoService service;


    public ResponseEntity<AgendamentoComunicacaoResponseDTO> agendarComunicacao(AgendamentoComunicacaoRequestDTO body){

        var response = service.insert(body);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
