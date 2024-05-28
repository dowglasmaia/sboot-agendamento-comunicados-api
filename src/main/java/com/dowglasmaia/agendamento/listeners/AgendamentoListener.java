package com.dowglasmaia.agendamento.listeners;

import com.dowglasmaia.agendamento.documents.StatusAgendamento;
import com.dowglasmaia.agendamento.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AgendamentoListener {

    private final AgendamentoRepository repository;

    @KafkaListener(
          groupId = "status",
          topics = "agendamento-enviado",
          containerFactory = "agendamentoContainerFactory"
    )
    public void agendamentoListener(String agendamentoId){
        log.info("Recebida mensagem para atualizar o status do agendamento com ID: {}", agendamentoId);

        repository.findById(agendamentoId)
              .map(agendamento -> {
                  log.info("Agendamento encontrado para o ID: {}. Alterando status para ENVIADO.", agendamentoId);
                  agendamento.setStatus(StatusAgendamento.ENVIADO);
                  return repository.save(agendamento);
              })
              .ifPresentOrElse(
                    agendamento -> log.info("Agendamento com ID: {} atualizado com sucesso.", agendamentoId),
                    () -> log.warn("Agendamento com ID: {} não encontrado.", agendamentoId)
              );
    }


}
