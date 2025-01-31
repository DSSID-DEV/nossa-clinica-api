package com.nossaclinica_api.models.dtos.requests.solicitacoes;

import lombok.Data;

import java.util.List;

@Data
public class RequestSolicitacaoEstornoDePagamento {
    private Long idAgendamento;
    private List<Long> formasDePagamento;
    private String solicitante;
    private String motivo;
}
