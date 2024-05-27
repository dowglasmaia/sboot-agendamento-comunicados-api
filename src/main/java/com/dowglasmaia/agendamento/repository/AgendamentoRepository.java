package com.dowglasmaia.agendamento.repository;

import com.dowglasmaia.agendamento.documents.AgendamentoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AgendamentoRepository extends MongoRepository<AgendamentoDocument, String> {
}
