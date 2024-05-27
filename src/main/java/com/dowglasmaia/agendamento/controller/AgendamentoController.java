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

import static com.dowglasmaia.agendamento.mapper.AgendamentoMapper.toAgendamentoDocument;
import static com.dowglasmaia.agendamento.mapper.AgendamentoMapper.toAgendamentoResponse;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AgendamentoController implements ComunicadosApi {

    private final AgendamentoService service;


    public ResponseEntity<AgendamentoComunicacaoResponseDTO> agendarComunicacao(AgendamentoComunicacaoRequestDTO body){

        var request = toAgendamentoDocument(body);

        var response = service.insert(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(toAgendamentoResponse(response));
    }


}
