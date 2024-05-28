package com.dowglasmaia.agendamento.service;

import com.dowglasmaia.provider.model.AgendamentoComunicacaoRequestDTO;
import com.dowglasmaia.provider.model.AgendamentoComunicacaoResponseDTO;
import com.dowglasmaia.provider.model.StatusComunicacaoResponseDTO;

public interface AgendamentoService {

    AgendamentoComunicacaoResponseDTO insert(AgendamentoComunicacaoRequestDTO document);

    StatusComunicacaoResponseDTO getStatusById(String id);

}
