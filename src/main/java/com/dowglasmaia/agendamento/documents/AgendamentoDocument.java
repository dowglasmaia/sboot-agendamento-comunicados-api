package com.dowglasmaia.agendamento.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "agendamento")
public class AgendamentoDocument {

    @Id
    private String id;
    private LocalDate dateEnvio;
    private LocalTime horaEnvio;
    private String destinatario;
    private String tipoDestinatario;
    private String mensagem;
    private Instant dataHoraAgendada;
}
