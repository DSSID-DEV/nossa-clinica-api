package com.nossaclinica_api.converters;

import com.nossaclinica_api.models.dtos.responses.ResponseId;
import com.nossaclinica_api.models.entities.*;

public class ConverteResponseId {
    public static ResponseId doEspecialista(Especialista medico) {
        return ResponseId.builder()
                .idEspecialista(medico.getIdEspecialista())
                .build();
    }

    public static ResponseId doCliente(Cliente cliente) {
        return ResponseId.builder()
                .idCliente(cliente.getIdCliente())
                .build();
    }

    public static ResponseId daEspecialidade(Especialidade especialidade) {
        return ResponseId.builder()
                .idEspecialidade(especialidade.getIdEspecialidade())
                .build();
    }

    public static ResponseId daEspecialidadeMedica(EspecialidadeMedica especialidade) {
        return ResponseId.builder()
                .idEspecialidadeMedica(especialidade.getIdEspecialidadeMedica())
                .build();
    }

    public static ResponseId doServico(Servico servico) {
        return ResponseId.builder()
                .idServico(servico.getIdServico())
                .build();
    }

    public static ResponseId doServicoMedico(ServicosMedico servico) {
        return ResponseId.builder()
                .idServicoMedico(servico.getIdServicoMedico())
                .build();
    }

    public static ResponseId doAgendamento(Agendamento agendamento) {
        return ResponseId.builder()
                .idAgendamento(agendamento.getIdAgendamento())
                .build();
    }
    public static ResponseId doUsuario(User usuario) {
        return ResponseId.builder()
                .idUser(usuario.getIdUser())
                .build();
    }

    public static ResponseId doExame(Exame exame) {
        return ResponseId.builder()
                .idExame(exame.getIdExame())
                .build();
    }

    public static ResponseId daInstrucaoExame(Instrucao entity) {
        return ResponseId.builder()
                .idInstrucao(entity.getIdInstrucao())
                .build();
    }

    public static ResponseId doMovimentacao(Movimentacao entity) {
        return ResponseId.builder()
                .idMovimentacao(entity.getIdMovimentacao())
                .build();
    }
}
