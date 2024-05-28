package com.dowglasmaia.agendamento.controller;


import com.dowglasmaia.agendamento.mocks.MockBuild;
import com.dowglasmaia.agendamento.service.AgendamentoService;
import com.dowglasmaia.provider.model.AgendamentoComunicacaoRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.dowglasmaia.agendamento.mocks.MockBuild.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles(value = "test")
@WebMvcTest(controllers = AgendamentoController.class)
@AutoConfigureMockMvc
@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class AgendamentoControllerTest {

    final String URI_PATH = "/api/v1/comunicados/agendamentos";

    @Autowired
    MockMvc mvc;

    @MockBean
    private AgendamentoService service;


    @SneakyThrows
    @Test
    public void souldAgendarComunicacaoSuccessfully(){
        var requestMock = MockBuild.agendamentoComunicacaoRequestMock(
              "20/12/2222","02:02","maia@maia.com", AgendamentoComunicacaoRequestDTO.TipoDestinatarioEnum.EMAIL
        );
        var responseMock = agendamentoComunicacaoResponseMock();

        when(service.insert(any(AgendamentoComunicacaoRequestDTO.class)))
              .thenReturn(responseMock);

        String json = new ObjectMapper().writeValueAsString(requestMock);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
              .post(URI_PATH)
              .contentType(MediaType.APPLICATION_JSON)
              .accept(MediaType.APPLICATION_JSON)
              .content(json);

        mvc.perform(request)
              .andExpect(status().isCreated())
              .andExpect(jsonPath("$.id").value(responseMock.getId()));
    }

    @SneakyThrows
    @Test
    public void souldConsultarStatusComunicacaoSuccessfully(){
        var responseMock = statusAgendamentoComunicacaoResponseMock();

        when(service.getStatusById(anyString()))
              .thenReturn(responseMock);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
              .get(URI_PATH.concat("/status"))
              .param("id", "id-agendamento-test")
              .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.status").value(responseMock.getStatus()));
    }

    @SneakyThrows
    @Test
    public void souldRemoverComunicacaoSuccessfully(){
        doNothing().when(service).removeAgendamento(anyString());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
              .delete(URI_PATH)
              .param("id", "id-agendamento-test")
              .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
              .andExpect(status().isNoContent());
    }


}
