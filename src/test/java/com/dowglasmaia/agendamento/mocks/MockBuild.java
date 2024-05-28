package com.dowglasmaia.agendamento.mocks;

import com.dowglasmaia.provider.model.AgendamentoComunicacaoRequestDTO;
import com.dowglasmaia.provider.model.AgendamentoComunicacaoResponseDTO;
import com.dowglasmaia.provider.model.StatusComunicacaoResponseDTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class MockBuild {

    public static AgendamentoComunicacaoRequestDTO agendamentoComunicacaoRequestMock(){
        AgendamentoComunicacaoRequestDTO request = new AgendamentoComunicacaoRequestDTO();
        request.setDataEnvio(String.valueOf(LocalDate.now()));
        request.setHoraEnvio(String.valueOf(LocalTime.now()));
        request.setDestinatario("dowglasmaia@email.com");
        request.setTipoDestinatario(AgendamentoComunicacaoRequestDTO.TipoDestinatarioEnum.EMAIL);
        request.setMensagem("Este é um texto de exemplo para testar um comprimento de exatamente trezentos caracteres. Vamos preencher com mais algumas palavras e frases para garantir que atingimos o número desejado. Continuamos a escrever até que finalmente alcançamos o limite estabelecido para este teste específico.");
        return request;
    }

    public static AgendamentoComunicacaoResponseDTO agendamentoComunicacaoResponseMock(){
        AgendamentoComunicacaoResponseDTO response = new AgendamentoComunicacaoResponseDTO();
        response.setId("6655f2ef260a1c0f8dc2e5b2");
        return response;
    }

    public static StatusComunicacaoResponseDTO statusAgendamentoComunicacaoResponseMock(){
        StatusComunicacaoResponseDTO response = new StatusComunicacaoResponseDTO();
        response.setStatus("AGENDADO");
        return response;
    }

}
