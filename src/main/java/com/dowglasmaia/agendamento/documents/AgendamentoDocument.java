package com.dowglasmaia.agendamento.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;


@Data
@Document(collection = "agendamento")
public class AgendamentoDocument {

    @Id
    private String id;
    private LocalDateTime dateHoraEnvio;
    private String destinatario;
    private String tipoDestinatario;
    private String mensagem;
    private Instant dataHoraAgendada;
    private StatusAgendamento status;

    public AgendamentoDocument(){
        this.status = StatusAgendamento.AGENDADO;
    }
}
