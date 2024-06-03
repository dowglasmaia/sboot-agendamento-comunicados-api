package com.dowglasmaia.agendamento.controller;


import com.dowglasmaia.agendamento.service.AgendamentoService;
import com.dowglasmaia.provider.api.ComunicadosApi;
import com.dowglasmaia.provider.model.AgendamentoComunicacaoRequestDTO;
import com.dowglasmaia.provider.model.AgendamentoComunicacaoResponseDTO;
import com.dowglasmaia.provider.model.StatusComunicacaoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AgendamentoController implements ComunicadosApi {

    private final AgendamentoService service;


    @PreAuthorize("hasAnyAuthority('ADMIN_READ','ADMIN_WRITE')")
    //@PreAuthorize("(hasAuthority('ADMIN_READ') and hasAuthority('ADMIN_WRITE')) or (hasAuthority('OPERATION_WRITE') and hasAuthority('OPERATION_READ'))")
    public ResponseEntity<AgendamentoComunicacaoResponseDTO> agendarComunicacao(AgendamentoComunicacaoRequestDTO body){
        return ResponseEntity
              .status(HttpStatus.CREATED)
              .body(service.insert(body));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN_READ','ADMIN_WRITE')")
    public ResponseEntity<StatusComunicacaoResponseDTO> consultarStatusComunicacao(String id){
        return ResponseEntity
              .status(HttpStatus.OK)
              .body(service.getStatusById(id));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN_READ','ADMIN_WRITE')")
    public ResponseEntity<Void> removerComunicacao(String id){
        service.removeAgendamento(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
