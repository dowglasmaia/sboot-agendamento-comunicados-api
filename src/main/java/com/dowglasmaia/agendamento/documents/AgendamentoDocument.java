package com.dowglasmaia.agendamento.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
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
}
