package com.dowglasmaia.agendamento.mapper;

import com.dowglasmaia.agendamento.documents.AgendamentoDocument;
import com.dowglasmaia.agendamento.util.ValidaDateHora;
import com.dowglasmaia.provider.model.AgendamentoComunicacaoRequestDTO;
import com.dowglasmaia.provider.model.AgendamentoComunicacaoResponseDTO;

import java.time.Instant;
import java.time.LocalDateTime;


public class AgendamentoMapper {

    public static AgendamentoDocument toAgendamentoDocument(AgendamentoComunicacaoRequestDTO comunicacaoRequest){

        LocalDateTime dataHora = ValidaDateHora.validaDataHoraFutura(comunicacaoRequest.getDataEnvio(), comunicacaoRequest.getHoraEnvio());

        AgendamentoDocument document = new AgendamentoDocument();
        document.setDestinatario(comunicacaoRequest.getDestinatario());
        document.setTipoDestinatario(comunicacaoRequest.getTipoDestinatario().name());
        document.setMensagem(comunicacaoRequest.getMensagem());

        document.setDateHoraEnvio(dataHora);

        document.setDataHoraAgendada(Instant.now());
        return document;
    }

    public static AgendamentoComunicacaoResponseDTO toAgendamentoResponse(AgendamentoDocument document){
        AgendamentoComunicacaoResponseDTO response = new AgendamentoComunicacaoResponseDTO();
        response.setId(document.getId());
        return response;
    }
}
