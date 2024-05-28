package com.dowglasmaia.agendamento.service.impl;

import com.dowglasmaia.agendamento.execptions.BusinessException;
import com.dowglasmaia.agendamento.repository.AgendamentoRepository;
import com.dowglasmaia.agendamento.service.AgendamentoService;
import com.dowglasmaia.agendamento.util.ValidaTipoDestinatarioEnvio;
import com.dowglasmaia.provider.model.AgendamentoComunicacaoRequestDTO;
import com.dowglasmaia.provider.model.AgendamentoComunicacaoResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.dowglasmaia.agendamento.mapper.AgendamentoMapper.toAgendamentoDocument;
import static com.dowglasmaia.agendamento.mapper.AgendamentoMapper.toAgendamentoResponse;

@Slf4j
@RequiredArgsConstructor
@Service
public class AgendamentoServiceImpl implements AgendamentoService {

    private final AgendamentoRepository repository;

    @Override
    public AgendamentoComunicacaoResponseDTO insert(AgendamentoComunicacaoRequestDTO comunicacaoRequest){

        if (ValidaTipoDestinatarioEnvio.validaDestinatario(
              comunicacaoRequest.getDestinatario(),
              comunicacaoRequest.getTipoDestinatario())
        ) {
            var requestMapper = toAgendamentoDocument(comunicacaoRequest);

            try {
                var document = repository.insert(requestMapper);
                return toAgendamentoResponse(document);

            } catch (Exception e) {
                log.error("Falhou em criar agendamento para solicitação: {}", comunicacaoRequest, e);
                throw new BusinessException("Falhou em criar agendamento", HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }
        throw new IllegalArgumentException("Destinatário ou tipo inválido: " + comunicacaoRequest.getDestinatario() + ", " + comunicacaoRequest.getTipoDestinatario());
    }

}
