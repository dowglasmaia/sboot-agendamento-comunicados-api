package com.dowglasmaia.agendamento.service.impl;

import com.dowglasmaia.agendamento.documents.AgendamentoDocument;
import com.dowglasmaia.agendamento.execptions.BusinessException;
import com.dowglasmaia.agendamento.repository.AgendamentoRepository;
import com.dowglasmaia.agendamento.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AgendamentoServiceImpl implements AgendamentoService {

    private final AgendamentoRepository repository;

    @Override
    public AgendamentoDocument insert(AgendamentoDocument document){
        try {
            return repository.insert(document);
        } catch (Exception e) {
            throw new BusinessException("Falha ao criar Agendamento", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
