package com.nossaclinica_api.scheduled;

import com.nossaclinica_api.reports.utils.ConstantesValues;
import com.nossaclinica_api.services.AgendamentoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class TarefasAgendadas {

    private final AgendamentoService agendamentos;

    @Scheduled(cron = "0 0 17 * * ?")
    public void enviarAgendaDoMedico() {
        log.info(ConstantesValues.INICIO_DA_TAREFA_DE_ENVIO_DE_MENSAGEM.formatted(LocalDateTime.now()));
        agendamentos.buscarAgendaMedica();
        log.info(ConstantesValues.FIM_DA_TAREFA_DE_ENVIO_DE_MENSAGEM.formatted(LocalDateTime.now()));
    }

    @Scheduled(cron = "0 0 8 * * ?")
    public void enviarLembreteDaAgendaDoDiaSeguinteAoCliente() {
        log.info(ConstantesValues.INICIO_DA_TAREFA_DE_ENVIO_DE_MENSAGEM.formatted(LocalDateTime.now()));
        agendamentos.buscarPacientesAgendadosDoDiaSeguinte();
        log.info(ConstantesValues.FIM_DA_TAREFA_DE_ENVIO_DE_MENSAGEM.formatted(LocalDateTime.now()));
    }



}
