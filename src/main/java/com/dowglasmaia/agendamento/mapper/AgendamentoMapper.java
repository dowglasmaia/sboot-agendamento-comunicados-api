package com.dowglasmaia.agendamento.mapper;

import com.dowglasmaia.agendamento.documents.AgendamentoDocument;
import com.dowglasmaia.provider.model.AgendamentoComunicacaoRequestDTO;
import com.dowglasmaia.provider.model.AgendamentoComunicacaoResponseDTO;

import java.time.Instant;

import static com.dowglasmaia.agendamento.util.DateConverter.convertStringToLocalDate;
import static com.dowglasmaia.agendamento.util.DateConverter.convertStringToLocalTime;

public class AgendamentoMapper {

    public static AgendamentoDocument toAgendamentoDocument(AgendamentoComunicacaoRequestDTO comunicacaoRequest){
        AgendamentoDocument document = new AgendamentoDocument();
        document.setDestinatario(comunicacaoRequest.getDestinatario());
        document.setTipoDestinatario(comunicacaoRequest.getTipoDestinatario().name());
        document.setMensagem(comunicacaoRequest.getMensagem());
        document.setDateEnvio(convertStringToLocalDate(comunicacaoRequest.getDataEnvio()));
        document.setHoraEnvio(convertStringToLocalTime(comunicacaoRequest.getHoraEnvio()));

        document.setDataHoraAgendada(Instant.now());
        return document;
    }

    public static AgendamentoComunicacaoResponseDTO toAgendamentoResponse(AgendamentoDocument document){
        AgendamentoComunicacaoResponseDTO response = new AgendamentoComunicacaoResponseDTO();
        response.setId(document.getId());
        return response;
    }
}
