package com.dowglasmaia.agendamento.service;

import com.dowglasmaia.provider.model.AgendamentoComunicacaoRequestDTO;
import com.dowglasmaia.provider.model.AgendamentoComunicacaoResponseDTO;

public interface AgendamentoService {

    AgendamentoComunicacaoResponseDTO insert(AgendamentoComunicacaoRequestDTO document);


}
