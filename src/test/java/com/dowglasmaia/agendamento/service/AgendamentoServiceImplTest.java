package com.dowglasmaia.agendamento.service;


import com.dowglasmaia.agendamento.documents.AgendamentoDocument;
import com.dowglasmaia.agendamento.documents.StatusAgendamento;
import com.dowglasmaia.agendamento.execptions.BusinessException;
import com.dowglasmaia.agendamento.mocks.MockBuild;
import com.dowglasmaia.agendamento.repository.AgendamentoRepository;
import com.dowglasmaia.agendamento.service.impl.AgendamentoServiceImpl;
import com.dowglasmaia.provider.model.AgendamentoComunicacaoResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.dowglasmaia.provider.model.AgendamentoComunicacaoRequestDTO.TipoDestinatarioEnum;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class AgendamentoServiceImplTest {

    @MockBean
    private AgendamentoRepository repository;

    private AgendamentoServiceImpl service;

    @BeforeEach
    public void setUp(){
        this.service = new AgendamentoServiceImpl(repository);
    }

    @Test
    public void souldInsertValidRequestReturnsResponse(){
        var requestDTO = MockBuild.agendamentoComunicacaoRequestMock(
              "20/12/2222", "02:02", "maia@maia.com", TipoDestinatarioEnum.EMAIL
        );
        AgendamentoDocument document = new AgendamentoDocument();
        document.setId("6655f2ef260a1c0f8dc2e5b2");

        when(repository.save(Mockito.any(AgendamentoDocument.class)))
              .thenReturn(document);

        AgendamentoComunicacaoResponseDTO responseDTO = service.insert(requestDTO);

        assertNotNull(responseDTO);

        verify(repository, times(1)).save(any(AgendamentoDocument.class));
    }

    @Test
    public void souldInsertInvalidRequestThrowsException(){
        var requestDTO = MockBuild.agendamentoComunicacaoRequestMock(
              "20/12/2222", "02:02", "maia@maia.com", TipoDestinatarioEnum.SMS
        );

        var exception = assertThrows(IllegalArgumentException.class, () -> {
            service.insert(requestDTO);
        });

        assertEquals("Destinatário ou tipo inválido: " + requestDTO.getDestinatario() + ", " + requestDTO.getTipoDestinatario(), exception.getMessage());
    }

    @Test
    public void souldGetStatusById(){
        String id = "6655f2ef260a1c0f8dc2e5b2";
        AgendamentoDocument document = new AgendamentoDocument();
        document.setId(id);
        document.setStatus(StatusAgendamento.AGENDADO);

        when(repository.findById(Mockito.any(String.class)))
              .thenReturn(Optional.of(document));

        var response = service.getStatusById(id);

        String statusAtual = response.getStatus();
        String statusEsperado = StatusAgendamento.AGENDADO.name();

        assertNotNull(response);
        assertEquals(statusEsperado, statusAtual);

        verify(repository, times(1)).findById(any(String.class));
    }

    @Test
    public void souldRemoveAgendamento(){
        String id = "6655f2ef260a1c0f8dc2e5b2";
        AgendamentoDocument document = new AgendamentoDocument();
        document.setId(id);
        document.setStatus(StatusAgendamento.CANCELADO);

        when(repository.findById(Mockito.any(String.class)))
              .thenReturn(Optional.of(document));

        when(repository.save(Mockito.any(AgendamentoDocument.class)))
              .thenReturn(document);

        service.removeAgendamento(id);

        assertDoesNotThrow(() ->
              service.removeAgendamento(id)
        );
    }

    @Test
    public void souldRemoveAgendamentoThrowsException(){
        String id = "6655f2ef260a1c0f8dc2e5b2";

        when(repository.findById(Mockito.any(String.class)))
              .thenReturn(Optional.empty());

        var exception = assertThrows(BusinessException.class, () -> {
            service.removeAgendamento(id);
        });

        assertEquals("Agendamento não encontrado", exception.getMessage());
    }

}
